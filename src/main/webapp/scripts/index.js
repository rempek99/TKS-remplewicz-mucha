function myFunction() {
    let x = document.getElementById("navBar");
    if (x.className === "topNavigationBar") {
        x.className += " responsive";
    } else {
        x.className = "topNavigationBar";
    }
}