/* http://keith-wood.name/calendars.html
   Taiwanese (Minguo) calendar for jQuery v1.1.4.
   Written by Keith Wood (kbwood{at}iinet.com.au) February 2010.
   Dual licensed under the GPL (http://dev.jquery.com/browser/trunk/jquery/GPL-LICENSE.txt) and 
   MIT (http://dev.jquery.com/browser/trunk/jquery/MIT-LICENSE.txt) licenses. 
   Please attribute the author if you use it. */
(function($){var e=$.calendars.instance();function TaiwanCalendar(a){this.local=this.regional[a||'']||this.regional['']}TaiwanCalendar.prototype=new $.calendars.baseCalendar;$.extend(TaiwanCalendar.prototype,{name:'Taiwan',jdEpoch:2419402.5,yearsOffset:1911,daysPerMonth:[31,28,31,30,31,30,31,31,30,31,30,31],hasYearZero:false,minMonth:1,firstMonth:1,minDay:1,regional:{'':{name:'Taiwan',epochs:['BROC','ROC'],monthNames:['January','February','March','April','May','June','July','August','September','October','November','December'],monthNamesShort:['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],dayNames:['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'],dayNamesShort:['Sun','Mon','Tue','Wed','Thu','Fri','Sat'],dayNamesMin:['Su','Mo','Tu','We','Th','Fr','Sa'],dateFormat:'yyyy/mm/dd',firstDay:1,isRTL:false}},leapYear:function(a){var b=this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidYear);var a=this._t2gYear(b.year());return e.leapYear(a)},weekOfYear:function(a,b,c){var d=this._validate(a,this.minMonth,this.minDay,$.calendars.local.invalidYear);var a=this._t2gYear(d.year());return e.weekOfYear(a,d.month(),d.day())},daysInMonth:function(a,b){var c=this._validate(a,b,this.minDay,$.calendars.local.invalidMonth);return this.daysPerMonth[c.month()-1]+(c.month()==2&&this.leapYear(c.year())?1:0)},weekDay:function(a,b,c){return(this.dayOfWeek(a,b,c)||7)<6},toJD:function(a,b,c){var d=this._validate(a,b,c,$.calendars.local.invalidDate);var a=this._t2gYear(d.year());return e.toJD(a,d.month(),d.day())},fromJD:function(a){var b=e.fromJD(a);var c=this._g2tYear(b.year());return this.newDate(c,b.month(),b.day())},_t2gYear:function(a){return a+this.yearsOffset+(a>=-this.yearsOffset&&a<=-1?1:0)},_g2tYear:function(a){return a-this.yearsOffset-(a>=1&&a<=this.yearsOffset?1:0)}});$.calendars.calendars.taiwan=TaiwanCalendar})(jQuery);