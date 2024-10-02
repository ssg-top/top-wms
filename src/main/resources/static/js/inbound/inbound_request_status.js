document.addEventListener('DOMContentLoaded', () => {
  const pageLinks = document.querySelectorAll('.pagination .page-item');

  pageLinks.forEach(link => {
    link.addEventListener('click', function(event) {
      event.preventDefault(); // 기본 클릭 동작 방지

      pageLinks.forEach(item => item.classList.remove('active'));

      this.classList.add('active');
    });
  });
});
