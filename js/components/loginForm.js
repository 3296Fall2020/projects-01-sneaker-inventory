function loginForm () {

    var accountDiv = document.createElement("div");
    accountDiv.classList.add("account");

    //create break line element
    var br = document.createElement("br");

    //create a form
    var form = document.createElement("form");
    form.setAttribute("name", "myForm");
    form.onsubmit = function loginSubmit() {

        let myForm = document.forms["myForm"];
        let formData = new FormData(myForm);

        var object = {};
        formData.forEach(function(value, key){
            object[key] = value;
        });
        var json = JSON.stringify(object);

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance

        var theUrl = "https://54.172.190.202:443/login";

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200){
                console.log(this.responseText);
                var result = JSON.parse(xmlhttp.response);
                if (result.sessionID == 'login'){
                    alert("Login Success!");
                    //sessionStorage.setItem("id", object.id)
                    window.location.hash = "#/sneakerInventory";
                }else{
                    alert(result.sessionID);
                }
            }
        };

        xmlhttp.open("POST", theUrl);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(json);

        return false;
    };

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
    form.appendChild(br.cloneNode());

    accountDiv.appendChild(form);

    return accountDiv;

}