$(document).ready(function () {
    var buttonAdmin = document.getElementById("adminBtn");
    var buttonAdminInfo = document.getElementById("adminInfoBtn");

    var div = document.getElementById("panelDiv");

    var adminDiv = document.getElementById("admin-panel");
    var adminInfoDiv = document.getElementById("admin-info-panel");
    adminInfoDiv.style.display = "none";

    $(document).delegate('#adminBtn', 'click', function () {
        buttonAdmin.className = "nav-link active btn btn-lg btn-primary btn-block text-left";
        buttonAdminInfo.className = "nav-link mt-2 btn btn-lg text-primary btn-block text-left";

        adminInfoDiv.style.display = "none";
        adminDiv.style.display = "block";
        div.append(adminDiv);
    });

    $(document).delegate('#adminInfoBtn', 'click', function () {
        buttonAdmin.className = "nav-link btn btn-lg text-primary btn-block text-left";
        buttonAdminInfo.className = "nav-link active mt-2 btn btn-lg btn-primary btn-block text-left";

        adminDiv.style.display = "none";
        adminInfoDiv.style.display = "block";
        div.append(adminInfoDiv);
    });
});