
$(document).ready(function(){

// Number of seconds in every time division
    var years  = 31556926,//年份，按按回归年365天5时48分46秒算
        months  = 2629744,//月份，以实际平均每月秒数计算
        days	= 24*60*60,
        hours	= 60*60,
        minutes	= 60;

    // Creating the plugin
    $.fn.countdown = function(prop){

        var options = $.extend({
            callback	: function(){},
            timestamp	: 0
        },prop);

        var left, y, mon, d, h, m, s, positions;

        // Initialize the plugin
        init(this, options);

        positions = this.find('.position');

        (function tick(){

            // Time left
            left = Math.floor((options.timestamp - (new Date())) / 1000);

            if(left < 0){
                left = 0;
            }

            //Number of years left
            y = Math.floor(left / years);
            updateDuo(0, 1, y);
            left -= y*years;

            //Number of months left
            mon = Math.floor(left / months);
            updateDuo(2, 3, mon);
            left -= mon*months;

            // Number of days left
            d = Math.floor(left / days);
            updateDuo(4, 5, d);
            left -= d*days;

            // Number of hours left
            h = Math.floor(left / hours);
            updateDuo(6, 7, h);
            left -= h*hours;

            // Number of minutes left
            m = Math.floor(left / minutes);
            updateDuo(8, 9, m);
            left -= m*minutes;

            // Number of seconds left
            s = left;
            updateDuo(10, 11, s);

            // Calling an optional user supplied callback
            options.callback(y,mon,d, h, m, s);

            // Scheduling another call of this function in 1s
            setTimeout(tick, 1000);
        })();

        // This function updates two digit positions at once
        function updateDuo(minor,major,value){
            switchDigit(positions.eq(minor),Math.floor(value/10)%10);
            switchDigit(positions.eq(major),value%10);
        }

        return this;
    };


    function init(elem, options){
        elem.addClass('countdownHolder');

        // Creating the markup inside the container
        $.each(['Years','Months','Days','Hours','Minutes','Seconds'],function(i){
            var boxName;
            if(this=="Years") {
                boxName = "年";
            }
            else if(this=="Months") {
                boxName = "月";
            }
            else if(this=="Days") {
                boxName = "天";
            }
            else if(this=="Hours") {
                boxName = "时";
            }
            else if(this=="Minutes") {
                boxName = "分";
            }
            else {
                boxName = "秒";
            }
            $('<div class="count'+this+'">' +
                '<span class="position">' +
                '<span class="digit static">0</span>' +
                '</span>' +
                '<span class="position">' +
                '<span class="digit static">0</span>' +
                '</span>' +
                '<span class="boxName">' +
                '<span class="'+this+'">' + boxName + '</span>' +
                '</span>'
            ).appendTo(elem);

            if(this!="Seconds"){
                elem.append('<span class="points">:</span><span class="countDiv countDiv'+i+'"></span>');
            }
        });

    }

    // Creates an animated transition between the two numbers
    function switchDigit(position,number){

        var digit = position.find('.digit')

        if(digit.is(':animated')){
            return false;
        }

        if(position.data('digit') == number){
            // We are already showing this number
            return false;
        }

        position.data('digit', number);

        var replacement = $('<span>',{
            'class':'digit',
            css:{
                top:0,
                opacity:0
            },
            html:number
        });

        // The .static class is added when the animation
        // completes. This makes it run smoother.

        digit
            .before(replacement)
            .removeClass('static')
            .animate({top:0,opacity:0},'fast',function(){
                digit.remove();
            })

        replacement
            .delay(100)
            .animate({top:0,opacity:1},'fast',function(){
                replacement.addClass('static');
            });
    }

	/* ---- Countdown timer ---- */

	$('#counter').countdown({
		timestamp :  Date.UTC(2019, 2, 16, 8)
	});


	/* ---- Animations ---- */

	$('#links a').hover(
		function(){ $(this).animate({ left: 3 }, 'fast'); },
		function(){ $(this).animate({ left: 0 }, 'fast'); }
	);

	$('footer a').hover(
		function(){ $(this).animate({ top: 3 }, 'fast'); },
		function(){ $(this).animate({ top: 0 }, 'fast'); }
	);


	/* ---- Using Modernizr to check if the "required" and "placeholder" attributes are supported ---- */



	// for detecting if the browser is Safari
	/*var browser = navigator.userAgent.toLowerCase();

	if(!Modernizr.input.required || (browser.indexOf("safari") != -1 && browser.indexOf("chrome") == -1)) {
		$('.form').submit(function() {
			$('.popup').remove();
			if(!$('.email').val() || $('.email').val() == 'Input your e-mail address here...') {
				$('form').append('<p class="popup">Please fill out this field.</p>');
				$('.email').focus();
				return false;
			}
			alert("你好");
		});
		$('.email').keydown(function() {
			$('.popup').remove();
		});
		$('.email').blur(function() {
			$('.popup').remove();
		});
	}*/


});
