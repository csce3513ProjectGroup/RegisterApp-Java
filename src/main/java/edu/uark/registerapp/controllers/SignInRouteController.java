package edu.uark.registerapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.employees.ActiveEmployeeExistsQuery;
import edu.uark.registerapp.commands.employees.EmployeeSignInCommand;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.controllers.enums.QueryParameterNames;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.EmployeeSignIn;

import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class SignInRouteController extends BaseRouteController {
	//Route for initial page load
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showSignIn(@RequestParma final Map<String, String> queryParameters)
	{
		try {
			this.activeEmployeeExistsQuery.execute();
		} catch (NotFoundException e) {
			return new ModelAndView(
				REDIRECT_PREPEND.concat(
					ViewNames.EMPLOYEE_DETAIL.getRoute()));
		}

		ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.SIGN_IN.getViewName()),
				queryParameters);
		
		if (queryParameters.containsKey(QueryParameterNames.EMPLOYEE_ID.getValue())) {
			modelAndView.addObject(
				ViewModelNames.EMPLOYEE_ID.getValue(),
				queryParameters.get(QueryParameterNames.EMPLOYEE_ID.getValue()));
		}

		return modelAndView;
	}


	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView performSignIn(
		// TODO: Define an object that will represent the sign in request and add it as a parameter here
		EmployeesSignIn employeeSignIn,
		HttpServletRequest request
	) {


		// sign in the user
		try {
			this.employeeSignInCommand
				.setSessionId(request.getSession().getId())
				.setEmployeeSignIn(employeeSignIn)
				.execute();
		} catch (Exception e) {
			ModelAndView modelAndView =
				new ModelAndView(ViewNames.SIGN_IN.getViewName());

			modelAndView.addObject(
				ViewModelNames.ERROR_MESSAGE.getValue(),
				e.getMessage());
			modelAndView.addObject(
				ViewModelNames.EMPLOYEE_ID.getValue(),
				employeeSignIn.getEmployeeId());

			return modelAndView;
		}

		return new ModelAndView(
			REDIRECT_PREPEND.concat(
				ViewNames.MAIN_MENU.getRoute()));
	}

		return new ModelAndView(
			REDIRECT_PREPEND.concat(
				ViewNames.MAIN_MENU.getRoute()));
	}
	//Properties
	@Autowired
	private EmployeeSignInCommand employeeSignInCommand;

	@Autowired
	private ActiveEmployeeExistsQuery activeEmployeeExistsQuery;
}
