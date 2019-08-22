(function ($) {
    "use strict"; // Start of use strict

    // Toggle the side navigation
    $("#sidebarToggle").on('click', function (e) {
        $("body").toggleClass("sidebar-toggled");
        $(".sidebar-brand .sidebar-brand-icon").toggleClass("d-none");
        $(".sidebar").toggleClass("toggled");
        if ($(".sidebar").hasClass("toggled")) {
            $('.sidebar .collapse').collapse('hide');
        }
        ;
    });

    // Close any open menu accordions when window is resized below 768px
    $(window).resize(function () {
        if ($(window).width() < 768) {
            $('.sidebar .collapse').collapse('hide');
        }
        ;
    });

    // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
    $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function (e) {
        if ($(window).width() > 768) {
            var e0 = e.originalEvent,
                delta = e0.wheelDelta || -e0.detail;
            this.scrollTop += (delta < 0 ? 1 : -1) * 30;
            e.preventDefault();
        }
    });

    // Scroll to top button appear
    $(document).on('scroll', function () {
        var scrollDistance = $(this).scrollTop();
        if (scrollDistance > 100) {
            $('.scroll-to-top').fadeIn();
        } else {
            $('.scroll-to-top').fadeOut();
        }
    });

    // Smooth scrolling using jQuery easing
    $(document).on('click', 'a.scroll-to-top', function (e) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top)
        }, 1000, 'easeInOutExpo');
        e.preventDefault();
    });

    // Set attributes of the progress-bars
    var progressBarList = $('.progress .progress-bar');
    progressBarList.each(function () {
        var now = $(this).attr('aria-valuenow');
        var all = $(this).attr('aria-valuemax');
        var part = (now / all * 100).toFixed(0);
        var percent = part + "%";
        $(this).css("width", percent);
        $(this).text(percent);
        $(this).addClass(getColor(part));
    });

    // Delete modal
    $('#deleteModal').on('show.bs.modal', function (event) {
        var itemId = $(event.relatedTarget).data('item-id');
        var itemName = $(event.relatedTarget).data('item-name');
        var itemType = $(event.relatedTarget).data('item-type');
        $(this).find('.modal-body p #itemName').text(itemName);
        $('#deleteId').on('click', function () {
            window.location.href = "/admin/" + itemType + "/rmv?id=" + itemId;
        })
    });

    $(document).on('click', 'span.back-button', function () {
        history.back();
    })



})(jQuery); // End of use strict

function getColor(percent) {
    if (percent == 0) {
        return "";
    } else if (percent < 20) {
        return "bg-secondary";
    } else if (percent < 40) {
        return "bg-warning";
    } else if (percent < 60) {
        return "bg-info";
    } else if (percent < 80) {
        return "bg-primary";
    } else {
        return "bg-success";
    }
}
