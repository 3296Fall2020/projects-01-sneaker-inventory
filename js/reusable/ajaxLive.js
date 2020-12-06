function ajaxLive(url, successCallBackFn, errorEle) {

    var httpReq;
    if (window.XMLHttpRequest) {
        httpReq = new XMLHttpRequest(); //For Firefox, Safari, Opera
        httpReq.withCredentials = true;
    } else if (window.ActiveXObject) {
        httpReq = new ActiveXObject("Microsoft.XMLHTTP"); //For IE 5+
    } else {
        alert('ajax not supported');
    }

    console.log("ready to get content " + url);
    httpReq.open("GET", url); // specify which page you want to get


    httpReq.onreadystatechange = function () {
        //console.log("in ajax, ready state is " + httpReq.readyState);

        if (httpReq.readyState === 4) {     // 4 means that the data transfer is complete
            console.log("in ajax, status is " + httpReq.status);
            if (httpReq.status === 500) {
                alert("You need to login to view inventory");
                window.location.hash = "#/login";
            }

            if (httpReq.status === 200) {   // 200 means file found (unlike 404 which means not found)
                console.log("in ajax, js object printed next");
                console.log(this.responseText);
                var obj = JSON.parse(httpReq.response);
                var list = JSON.stringify(obj.sneakerList);
                var returnObj = JSON.parse(list);
                //var obj = JSON.stringify(httpReq.response);
                console.log(returnObj);

                // Here we call back whichever function wanted us to make the AJAX call.
                successCallBackFn(returnObj);

            } else {  // error, file not found

                // One input parameter to this ajax function is a DOM element designed to hold any possible error message.
                // Populate it with as much information as we know about the error.
                errorEle.innerHTML = "Error " + httpReq.status + "-" + httpReq.statusText +
                    " while attempting to read '" + url + "'";
            }
        }
    }; // end of anonymous callback function definition

    httpReq.send(null); // initiate ajax call
    console.log("call initiated");


} // end function ajax