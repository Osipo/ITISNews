/**
 * Created by Олег on 07.11.2016.
 */
var totalCount = 3;
function ChangeIt()
{
    var num = Math.ceil( Math.random() * totalCount );//if we want, to show different gifs in random case.
    document.body.background = 'js/gifs/ErrorSe'+num+'.gif';
    document.body.style.backgroundRepeat = "repeat";// Background repeat
}
window.onload = ChangeIt;