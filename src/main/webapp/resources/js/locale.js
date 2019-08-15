document.addEventListener("DOMContentLoaded", function () {
   var locale = document.querySelectorAll('#locale span');
   locale.forEach(function (span) {
       span.addEventListener("click", function () {
           var chosenLang = this.dataset.value;
           var originPage = window.location.pathname;
           var location = window.location;
           window.location.replace(window.location.origin + '/international?lang=' + chosenLang + '&prev=' + originPage);
       })
   })
});