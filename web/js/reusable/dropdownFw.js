function dropdownFw(dropHeaderStyle, dropContentStyle, hiddenRight) {

    var ddObj = {}; // declare empty object (that will be returned at the end  of this function


    ddObj.dropdown = function (subMenuId) {

        // get a reference to the dropContent that is to be opened or closed
        var ele = document.getElementById(subMenuId);

        // in case any other dropContent is opened, close them.
        hideExcept(ele);

        // hide submenu if visible, else make it visible.
        toggle(ele);
    };

    function toggle(ele) { // hide the dropContent if showing, show if hiding

        if (ele.style.visibility === "visible") {
            hide(ele);
        } else {
            show(ele);
        }
    } // end function toggle

    function hide(ele) {  // make element invisible and move off to right
        ele.style.right = hiddenRight; // e.g., "-500px", however far off to right the user wants
        ele.style.visibility = "hidden";
    }

    function show(ele) { // make element visible and bring back from right
        ele.style.visibility = "visible";
        ele.style.right = "0px";
    }

    function hideExcept(ele) { // hide all dropContent elements, except for element 'ele'
        var dropContentList = document.getElementsByClassName(dropContentStyle);
        for (var i = 0; i < dropContentList.length; i++) {
            if (dropContentList[i] !== ele) {
                hide(dropContentList[i]);
            }
        }
    }

    // Make it so that whenever the user clicks something other than a dropHeader,
    // all dropContents are closed. (Runs only once when page is loaded.)
    window.onclick = function (event) {


        // if the user clicks something besides a dropHeader...
        if (!event.target.className.includes(dropHeaderStyle)) {

            // then close all dropContents
            hideExcept(null);
            console.log("hiding all drop contents");
        }
    };

    return ddObj;
}