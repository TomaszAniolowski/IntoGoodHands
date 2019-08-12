document.addEventListener('DOMContentLoaded', function (){
    var divToChangeContent = document.querySelector('div.slogan.container.container--90');
    var newContent = document.createElement('H2');
    newContent.innerText = 'Dziękujemy za przesłanie formularza. Na maila prześlemy wszelkie informacje o odbiorze.';
    divToChangeContent.replaceChild(newContent, divToChangeContent.querySelector('div.slogan--item'));
});