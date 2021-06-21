function menuFunction() {
    const x = document.getElementById("myMenu");
    if (x.className === "header") {
        x.className += " responsive";
    } else {
        x.className = "header";
    }
}