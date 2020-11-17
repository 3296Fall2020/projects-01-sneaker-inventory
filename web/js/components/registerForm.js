function registerForm () {

    var accountDiv = document.createElement("div");
    accountDiv.classList.add("account");

    //create break line element
    var br = document.createElement("br");

    //create a form
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "submit.php");

    //create an input element for User ID
    var id = document.createElement("input");
    id.setAttribute("type", "text");
    id.setAttribute("name", "id");
    id.setAttribute("placeholder", "User ID");
    id.setAttribute("required","true");
    id.setAttribute("maxlength","15");

    //create an input element for Password
    var password = document.createElement("input");
    password.setAttribute("type", "password");
    password.setAttribute("name", "password");
    password.setAttribute("placeholder", "Password");
    password.setAttribute("required","true");
    password.setAttribute("maxlength","15");

    //create an input element for Confirm Password
    var confirmPassword = document.createElement("input");
    confirmPassword.setAttribute("type", "password");
    confirmPassword.setAttribute("name", "confirmPassword");
    confirmPassword.setAttribute("placeholder", "Confirm Password");
    confirmPassword.setAttribute("required","true");
    confirmPassword.setAttribute("maxlength","15");


    //create a submit button
    var registerButton = document.createElement("input");
    registerButton.setAttribute("type", "submit");
    registerButton.setAttribute("value", "Register");

    form.appendChild(id);
    form.appendChild(br.cloneNode());
    form.appendChild(password);
    form.appendChild(br.cloneNode());
    form.appendChild(confirmPassword);
    form.appendChild(br.cloneNode());
    form.appendChild(registerButton);

    accountDiv.appendChild(form);

    return accountDiv;

}