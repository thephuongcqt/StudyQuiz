$(document).ready(function () {
    $('.validation').keypress(function () {
       validateSpecialCharacters('^[a-z0-9\_]+$');
    });
});

/* Validate special characters
 *
 *  @author: TrinhVQ
 *  @modified: 21/6/2017
 *  @param
 *  @return void
 */

function validateSpecialCharacters(regular) {
    var regex = new RegExp(regular);
    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    if (!regex.test(key)) {
        event.preventDefault();
        return false;
    }
}