package jspexercises.web.beans;

import jspexercises.domain.models.binding.UserLoginBindingModel;
import jspexercises.domain.models.service.UserServiceModel;
import jspexercises.service.user.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {

    private UserLoginBindingModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.model = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getModel() {
        return model;
    }

    public void setModel(UserLoginBindingModel model) {
        this.model = model;
    }

    public void login() throws IOException, ServletException {
        UserServiceModel userServiceModel = this.userService
                .loginUser(this.modelMapper.map(this.model, UserServiceModel.class));

        if (userServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();

        session.setAttribute("username", userServiceModel.getUsername());

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/home.xhtml");
    }

}
