$(document).ready(function () {
    $("#semester").change(function () {
        var val = $(this).val();
        if (val == "1") {
            $("#subjects").html("<option value='' disabled selected>Tantárgyak</option><option value='Digitális Alkalmazások'>Digitális Alkalmazások</option><option value='Lineáris Algebra'>Lineáris Algebra</option><option value='Programozási nyelvek 1'>Programozási nyelvek 1</option>");
        } else if (val == "2") {
            $("#subjects").html("<option value='' disabled selected>Tantárgyak</option><option value='Diszkrét Matematika'>Diszkrét Matematika</option><option value='Adatszerkezetes és algoritmusok'>Adatszerkezetes és algoritmusok</option><option value='Programozási nyelvek 2'>Programozási nyelvek 2</option>");
        }
    });
});