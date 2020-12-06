function deleteSneakerForm () {

    var insertDiv = document.createElement("div");
    insertDiv.classList.add("account");

    //create break line element
    var br = document.createElement("br");

    //create a form
    var form = document.createElement("form");
    form.setAttribute("name", "myForm");
    form.onsubmit = function() {

        let myForm = document.forms["myForm"];
        let formData = new FormData(myForm);

        var object = {};
        formData.forEach(function(value, key){
            object[key] = value;
        });
        var json = JSON.stringify(object);
        console.log(json);
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.withCredentials = true;
        var theUrl = "https://54.172.190.202:443/deleteSneaker";

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200){
                console.log(this.responseText);
                var result = JSON.parse(xmlhttp.response);
                if (result.sessionID == 'SessionID'){
                    alert("Sneaker edited");
                    window.location.hash = "#/sneakerInventoryLive";
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

    /*
        needed elements shoeName, sku, size, price, user_id

     */

    //create an input element for Index
    var index = document.createElement("input");
    index.setAttribute("type", "text");
    index.setAttribute("name", "index");
    index.setAttribute("placeholder", "Index");
    index.setAttribute("required","true");
    index.setAttribute("maxlength","45");

    //create an input element for Sneaker Name
    var shoeName = document.createElement("input");
    shoeName.setAttribute("type", "hidden");
    shoeName.setAttribute("name", "shoeName");
    shoeName.setAttribute("value", "name_of_sneaker");

    //create an input element for SKU
    var sku = document.createElement("input");
    sku.setAttribute("type", "hidden");
    sku.setAttribute("name", "sku");
    sku.setAttribute("value", "sku");

    //create an input element for Size
    var size = document.createElement("input");
    size.setAttribute("type", "hidden");
    size.setAttribute("name", "size");
    size.setAttribute("value", "size");

    //create an input element for Price
    var price = document.createElement("input");
    price.setAttribute("type", "hidden");
    price.setAttribute("name", "price");
    price.setAttribute("value", "price");

    //create a submit button
    var insertButton = document.createElement("input");
    insertButton.setAttribute("type", "submit");
    insertButton.setAttribute("value", "Delete Sneaker");


    form.appendChild(index);
    //form.appendChild(br.cloneNode());
    form.appendChild(shoeName);
    //form.appendChild(br.cloneNode());
    form.appendChild(sku);
    form.appendChild(br.cloneNode());
    form.appendChild(size);
    form.appendChild(br.cloneNode());
    form.appendChild(price);
    form.appendChild(br.cloneNode());
    form.appendChild(insertButton);

    insertDiv.appendChild(form);

    return insertDiv;

}