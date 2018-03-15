window.onload = function () {
    document.getElementById('content').addEventListener('keyup', function() {
        this.style.overflow = 'hidden';
        this.style.height = 0;
        this.style.height = this.scrollHeight + 'px';
    }, false);

    function resize() {
        var $area=document.getElementById('content');
        $area.style.overflow = 'hidden';
        $area.style.height = 0;
        $area.style.height = Math.max($area.scrollHeight, 86) + 'px';
    }
    resize();
};