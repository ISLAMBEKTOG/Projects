$(document).ready(function () {
    getListUsers();
    getAdminInfo();

    /* List of users */
    function getListUsers() {
        /* Clear all rows of table */
        $("#myTable").find("tr:gt(0)").remove();

        // DO GET
        $.ajax({
            type: "GET",
            url: "/api/read",
            success: function (json) {
                var tr = [];
                var url = "/api/read/";
                for (var i = 0; i < json.length; i++) {
                    tr.push('<tr>');
                    tr.push('<td>' + json[i].id + '</td>');
                    tr.push('<td>' + json[i].firstName + '</td>');
                    tr.push('<td>' + json[i].lastName + '</td>');
                    tr.push('<td>' + json[i].age + '</td>');
                    tr.push('<td>' + json[i].email + '</td>');
                    tr.push('<td>' + getRoleOfUserById(json[i].id) + '</td>');
                    tr.push('<td><a class=\'btn btn-info editBtn\' id=' + json[i].id + ' href = ' + url + json[i].id + '>Edit</a></td>');
                    tr.push('<td><a class=\'btn btn-danger deleteBtn\' id=' + json[i].id + ' href = ' + url + json[i].id + ' >Delete</a></td>');
                    tr.push('</tr>');
                }
                $("#myTable").append($(tr.join('')));
            }
        });
    }

    /* Admin Information table */
    function getAdminInfo() {
        var email = "";
        var roles = "";

        // DO GET
        $.ajax({
            type: "GET",
            url: "/api/authorizedUser",
            async: false,
            success: function (json) {
                var tr = [];
                tr.push('<tr>');
                tr.push('<td>' + json.id + '</td>');
                tr.push('<td>' + json.firstName + '</td>');
                tr.push('<td>' + json.lastName + '</td>');
                tr.push('<td>' + json.age + '</td>');
                tr.push('<td>' + json.email + '</td>');
                email = json.email;
                tr.push('<td>' + getRoleOfUserById(json.id) + '</td>');
                roles = getRoleOfUserById(json.id);
                tr.push('</tr>');
                $("#adminInfoTable").append($(tr.join('')));
            }
        });

        var p = document.getElementById("header-info");
        p.innerText = email + " with roles: " + roles;
    }

    // ADD FORM
    $("#myForm").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        addUser();
        $("form").trigger("reset");
    });

    // DELETE FORM
    $("#deleteForm").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        deleteUser();
    });

    // UPDATE FORM
    $("#editForm").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        editUser();
        $("form").trigger("reset");
    });

    /* Save user */
    function addUser() {
        // PREPARE FORM DATA
        var formData = {
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            age: $("#age").val(),
            email: $("#email").val(),
            password: $("#password").val(),
            role: $("#role option:selected").text()
        };

        // DO POST
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/save",
            data: JSON.stringify(formData),
            dataType: 'json'
        }).always(function () {
            getListUsers();
        });
    }

    /* Delete user */
    function deleteUser() {
        var id = $("#hiddendeleteId").val();

        // DO DELETE
        $.ajax({
            type: "DELETE",
            url: "/api/delete/" + id,
            cache: false,
            success: function () {
                $('#deleteModal').modal('hide');
                getListUsers();
            }
        })
    }

    /* Edit user */
    function editUser() {
        var formData = {
            id: $("#hiddenId").val(),
            firstName: $("#firstNameEdit").val(),
            lastName: $("#lastNameEdit").val(),
            age: $("#ageEdit").val(),
            email: $("#emailEdit").val(),
            password: $("#passwordEdit").val(),
            role: $("#roleEdit option:selected").text()
        };

        if (formData.password == null || formData.password == "") {
            formData.password = $("#passwordHiddenEdit").val();
        }

        // DO PUT
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/update",
            data: JSON.stringify(formData),
            dataType: 'json'
        }).always(function () {
            $('#editModal').modal('hide');
            getListUsers();
        });

    }

    /* List of roles of user */
    function getRoleOfUserById(id) {
        var roles = "";
        $.ajax({
            type: "GET",
            url: "/api/read/" + id + "/roles",
            async: false,
            success: function (result) {
                $.each(result, function (i, data) {
                    roles += data.name + " ";
                });
            }
        });
        return roles;
    }
});