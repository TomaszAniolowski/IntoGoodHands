document.addEventListener("DOMContentLoaded", function () {
   var locale = document.querySelectorAll('#locale img');
   locale.forEach(function (flag) {
       flag.addEventListener("click", function () {
           var chosenLang = this.dataset.value;
           var originPage = window.location.pathname;
           var location = window.location;
           window.location.replace(window.location.origin + '/international?lang=' + chosenLang + '&prev=' + originPage);
       });

       flag.addEventListener("mouseover", function () {
            flag.classList.toggle('flag-shadow')
       });

       flag.addEventListener("mouseout", function () {
           flag.classList.toggle('flag-shadow')

       });
   })
});