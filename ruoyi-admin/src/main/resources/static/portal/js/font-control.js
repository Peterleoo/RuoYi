// 字体大小控制脚本
(function() {
    // 初始化字体大小
    function initFontSize() {
        // 从localStorage读取保存的字体大小
        const savedFontSize = localStorage.getItem('fontSize') || 'medium';
        setFontSize(savedFontSize);
    }

    // 设置字体大小
    function setFontSize(size) {
        // 移除所有字体大小类
        document.body.classList.remove('font-small', 'font-medium', 'font-large');
        // 添加选中的字体大小类
        document.body.classList.add(`font-${size}`);
        // 保存到localStorage
        localStorage.setItem('fontSize', size);
        // 更新按钮状态
        updateFontButtons(size);
    }

    // 更新字体按钮状态
    function updateFontButtons(activeSize) {
        const buttons = document.querySelectorAll('.font-btn');
        buttons.forEach(btn => {
            btn.classList.remove('active');
            if (btn.dataset.size === activeSize) {
                btn.classList.add('active');
            }
        });
    }

    // 绑定按钮事件
    function bindEvents() {
        const buttons = document.querySelectorAll('.font-btn');
        buttons.forEach(btn => {
            btn.addEventListener('click', function() {
                const size = this.dataset.size;
                setFontSize(size);
            });
        });
    }

    // 页面加载完成后初始化
    document.addEventListener('DOMContentLoaded', function() {
        initFontSize();
        bindEvents();
    });
})();