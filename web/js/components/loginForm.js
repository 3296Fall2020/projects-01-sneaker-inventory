function loginForm () {

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

    //create an input element for Password
    var password = document.createElement("input");
    password.setAttribute("type", "password");
    password.setAttribute("name", "password");
    password.setAttribute("placeholder", "Password");
    password.setAttribute("required","true");

    //create a submit button
    var loginButton = document.createElement("input");
    loginButton.setAttribute("type", "submit");
    loginButton.setAttribute("value", "Log in");

    form.appendChild(id);
    form.appendChild(br.cloneNode());
    form.appendChild(password);
    form.appendChild(br.cloneNode());
    form.appendChild(loginButton);

    accountDiv.appendChild(form);

    return accountDiv;

}