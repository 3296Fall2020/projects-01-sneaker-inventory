function deleteSneaker () {

    var insertDiv = document.createElement("div");
    insertDiv.classList.add("account");

    //create break line element
    var br = document.createElement("br");

    //create a form
    var form = document.createElement("form");
    form.setAttribute("name", "myForm");
    form.onsubmit = function() {
        shoeName.setAttribute("disabled","false");
        sku.setAttribute("disabled","false");
        size.setAttribute("disabled","false");
        price.setAttribute("disabled","false");
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
                    alert("Sneaker deleted");
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
    shoeName.setAttribute("type", "text");
    shoeName.setAttribute("name", "shoeName");
    shoeName.setAttribute("value", "Sneaker Name");
    shoeName.setAttribute("disabled","true");
    shoeName.setAttribute("maxlength","45");

    //create an input element for SKU
    var sku = document.createElement("input");
    sku.setAttribute("type", "text");
    sku.setAttribute("name", "sku");
    sku.setAttribute("value", "SKU");
    sku.setAttribute("disabled","true");
    sku.setAttribute("maxlength","45");

    //create an input element for Size
    var size = document.createElement("input");
    size.setAttribute("type", "text");
    size.setAttribute("name", "size");
    size.setAttribute("value", "Size");
    size.setAttribute("disabled","true");
    size.setAttribute("maxlength","15");

    //create an input element for Price
    var price = document.createElement("input");
    price.setAttribute("type", "text");
    price.setAttribute("name", "price");
    price.setAttribute("value", "Price");
    price.setAttribute("disabled","true");
    price.setAttribute("maxlength","15");

    //create a submit button
    var insertButton = document.createElement("input");
    insertButton.setAttribute("type", "submit");
    insertButton.setAttribute("value", "Delete Sneaker");


    form.appendChild(index);
    form.appendChild(br.cloneNode());
    form.appendChild(shoeName);
    form.appendChild(br.cloneNode());
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