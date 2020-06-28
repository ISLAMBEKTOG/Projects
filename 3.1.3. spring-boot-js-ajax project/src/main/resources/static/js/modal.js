$(document).ready(function () {
    $(document).delegate('.editBtn', 'click', function () {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user) {
            $('#hiddenId').val(user.id);
            $('#idEdit').val(user.id);
            $('#firstNameEdit').val(user.firstName);
            $('#lastNameEdit').val(user.lastName);
            $('#ageEdit').val(user.age);
            $('#passwordHiddenEdit').val(user.password);
            $('#emailEdit').val(user.email);
        });
        $('#editModal').modal();
    });


    $(document).delegate('.deleteBtn', 'click', function () {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user) {
            $('#hiddendeleteId').val(user.id);
            $('#idDelete').val(user.id);
            $('#firstNameDelete').val(user.firstName);
            $('#lastNameDelete').val(user.lastName);
            $('#ageDelete').val(user.age);
            $('#emailDelete').val(user.email);
        });
        $('#deleteModal').modal();
    });
});