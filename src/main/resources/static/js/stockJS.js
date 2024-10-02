(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Sidebar Toggler
    $('.sidebar-toggler').click(function () {
        $('.sidebar, .content').toggleClass("open");
        return false;
    });


    // Progress Bar
    $('.pg-bar').waypoint(function () {
        $('.progress .progress-bar').each(function () {
            $(this).css("width", $(this).attr("aria-valuenow") + '%');
        });
    }, {offset: '80%'});


    // Calender
    $('#calender').datetimepicker({
        inline: true,
        format: 'L'
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        dots: true,
        loop: true,
        nav : false
    });


    
})(jQuery);



// 임시재고 마감 버튼 이벤트 리스너 추가 
document.addEventListener('DOMContentLoaded', function() {
  
    const closeButton = document.getElementById('closeButton');
    if (closeButton) {
        closeButton.addEventListener('click', function() {
            if (confirm('정말로 마감하시겠습니까?')) {
                fetch('/stock/update', {
                    method: 'GET',
                    headers: {
                        // 'X-Requested-With': 'XMLHttpRequest'
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert('마감이 완료되었습니다.');
                        window.location.reload(); // 페이지 새로고침
                    } else {
                        alert('마감 처리 중 오류가 발생했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('마감 처리 중 오류가 발생했습니다.');
                });
            }
        });
    }
});



document.addEventListener('DOMContentLoaded', function() {
    // 공통 요소
    const searchForm = document.getElementById('searchForm');
    const tempStocksearchForm = document.getElementById('tempStocksearchForm');
    
    
    const selectedItemsTableBody = document.getElementById('selectedItemsTableBody');
    
    const selectAllCheckbox = document.getElementById('selectAll');
    const pagination = document.querySelector('nav[aria-label="Page navigation"]');

     // 페이지 로드 시 초기 데이터 로드
     document.addEventListener('DOMContentLoaded', () => loadPage(1));

    // 검색 폼 제출 이벤트
    if (searchForm) {
        searchForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const formData = new FormData(this);
            const url = this.action + '?' + new URLSearchParams(formData).toString();
            fetchStockList(url);
        });
    }

    if (tempStocksearchForm) {
        tempStocksearchForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const formData = new FormData(this);
            const url = this.action + '?' + new URLSearchParams(formData).toString();
            fetchStockList(url);
            this.submit();
        });
    }

    // 재고 목록 가져오기
    function fetchStockList(url) {
        fetch(url, {
            method: 'GET',
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
        .then(response => response.json())
        .then(data => {
            updateStockTable(data.dtoList);
           // updatePagination(data);
        })
        .catch(error => console.error('Error:', error));
    }

    const stockTableBody = document.getElementById('stockTableBody');
    // 재고 테이블 업데이트
    function updateStockTable(stocks) {
        
        if (stockTableBody) {
            stockTableBody.innerHTML = '';
            stocks.forEach(stock => {
                const row = `
                    <tr>
                        <td><input type="checkbox" class="stock-checkbox" data-id="${stock.id}"></td>
                        <td>${stock.id}</td>
                        <td>${stock.productDTO?.productStorageType || ''}</td>
                        <td>${stock.productDTO?.name || ''}</td>
                        <td>${stock.stockAmount || ''}</td>
                        <td>${stock.memberDTO?.name || ''}</td>
                        <td>${stock.cellDTO?.warehouseDTO?.name || ''}</td>
                        <td>${stock.cellDTO?.warehouseDTO?.roadName || ''}</td>
                        <td>${new Date(stock.expirationDate).toLocaleDateString()}</td>
                    </tr>
                `;
                stockTableBody.insertAdjacentHTML('beforeend', row);
            });
        }
    }






    const searchstockcheckForm = document.getElementById('searchstockcheckForm');
    const applyStockCheckBtn = document.getElementById('applyStockCheckBtn');

    if (searchstockcheckForm) {
        searchstockcheckForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const formData = new FormData(this);
            const url = this.action + '?' + new URLSearchParams(formData).toString();
            fetchStoccheckList(url);
        });
    }

    // 재고실사 목록 가져오기
    function fetchStoccheckList(url) {
        fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('서버 응답 오류');
            }
            return response.json();
        })
        .then(data => {
            updateStockcheckTable(data.dtoList);
            updateStockcheckPagination(data);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('데이터를 불러오는 중 오류가 발생했습니다: ' + error.message);
        });
    }


    // 재고실사 테이블 업데이트
    function updateStockcheckTable(stockchecks) {
        const stockcheckTableBody = document.getElementById('stockcheckTableBody');
        if (stockcheckTableBody) {
            stockcheckTableBody.innerHTML = '';
            stockchecks.forEach(stockcheck => {
                const row = `
                    <tr>
                        // <td><input type="checkbox" class="stock-checkbox" data-id="${stockcheck.stockDTO.id}"></td>
                        <td>${stockcheck.stockDTO?.id}</td>
                        <td>${stockcheck.stockDTO?.productDTO?.productStorageType || ''}</td>
                        <td>${stockcheck.stockDTO?.productDTO?.name || ''}</td>
                        <td>${stockcheck.stockDTO?.stockAmount || ''}</td>
                        <td>${stockcheck.checkAmount || ''}</td>
                        <td>${stockcheck.stockDTO?.memberDTO?.name || ''}</td>
                        <td>${stockcheck.stockDTO?.cellDTO?.warehouseDTO?.name || ''}</td>
                        <td>${stockcheck.stockDTO?.cellDTO?.warehouseDTO?.roadName || ''}</td>
                        <td>${stockcheck.stockcheckState}</td>
                    </tr>
                `;
                stockTableBody.insertAdjacentHTML('beforeend', row);
            });
        }
    }



    //재고 실사 반영
    if (applyStockCheckBtn) {
        applyStockCheckBtn.addEventListener('click', function() {
            if (confirm('정말로 반영하시겠습니까?')) {
                fetch('/stockcheck/apply', {
                    method: 'GET',
                    headers: {
                        'X-Requested-With': 'XMLHttpRequest'
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert('재고테이블에 반영 되었습니다.');
                        window.location.reload();
                    } else {
                        alert('반영 처리 중 오류가 발생했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('반영 처리 중 오류가 발생했습니다.');
                });
            }
        });
    }



    function updateStockcheckPagination(data) {
        const pagination = document.querySelector('.pagination');
        if (pagination) {
            pagination.innerHTML = '';

            // 이전 페이지 링크
            const prevLink = `
                <li class="page-item ${data.prev ? '' : 'disabled'}">
                    <a class="page-link" href="#" data-page="${data.start - 1}">&laquo; 이전</a>
                </li>
            `;
            pagination.insertAdjacentHTML('beforeend', prevLink);

            // 페이지 번호 링크
            for (let i = data.start; i <= data.end; i++) {
                const pageLink = `
                    <li class="page-item ${data.page == i ? 'active' : ''}">
                        <a class="page-link" href="#" data-page="${i}">${i}</a>
                    </li>
                `;
                pagination.insertAdjacentHTML('beforeend', pageLink);
            }

            // 다음 페이지 링크
            const nextLink = `
                <li class="page-item ${data.next ? '' : 'disabled'}">
                    <a class="page-link" href="#" data-page="${data.end + 1}">다음 &raquo;</a>
                </li>
            `;
            pagination.insertAdjacentHTML('beforeend', nextLink);

            // 페이지 링크에 이벤트 리스너 추가
            pagination.querySelectorAll('.page-link').forEach(link => {
                link.addEventListener('click', function (e) {
                    e.preventDefault();
                    const page = this.dataset.page;
                    const url = `/stockcheck/list-stockcheck?page=${page}`;
                    fetchStoccheckList(url);
                });
            });
        }


      






        // 전체 선택 체크박스 이벤트
        if (selectAllCheckbox) {
            selectAllCheckbox.addEventListener('change', function () {
                const checkboxes = document.querySelectorAll('.stock-checkbox');
                checkboxes.forEach(checkbox => checkbox.checked = this.checked);
                updateSelectedItems();
            });
        }

        // 개별 체크박스 이벤트
        if (stockTableBody) {
            stockTableBody.addEventListener('change', function (e) {
                if (e.target.classList.contains('stock-checkbox')) {
                    console.log("checkBox!!!");
                    updateSelectedItems();
                }
            });
        }

        // 선택된 항목 업데이트
        function updateSelectedItems() {
            if (selectedItemsTableBody) {
                selectedItemsTableBody.innerHTML = '';
                const selectedCheckboxes = document.querySelectorAll('.stock-checkbox:checked');
                selectedCheckboxes.forEach(checkbox => {
                    const row = checkbox.closest('tr');
                    const newRow = `
                    <tr>               
                        <td>${row.cells[2].textContent}</td>
                        <td>${row.cells[3].textContent}</td>
                        <td>${row.cells[4].textContent}</td>
                        <td><input type="number" name="checkAmount" class="form-control" required></td>
                        <td>${row.cells[5].textContent}</td>
                        <td>${row.cells[6].textContent}</td>
                        <td>${row.cells[7].textContent}</td>
                        <td>${row.cells[8].textContent}</td>
                        <td>
                            <button type="button" class="btn btn-danger btn-sm cancel-selection" data-id="${checkbox.dataset.id}">취소</button>
                        </td>
                        <input type="hidden" name="stockId" value="${checkbox.dataset.id}">
                    </tr>
                `;
                    selectedItemsTableBody.insertAdjacentHTML('beforeend', newRow);
                });
            }
        }





        // 실사 등록에서 버튼
        if (selectedItemsTableBody) {
            selectedItemsTableBody.addEventListener('click', function (e) {
                if (e.target.classList.contains('cancel-selection')) {
                    const stockId = e.target.dataset.id;
                    const checkbox = document.querySelector(`.stock-checkbox[data-id="${stockId}"]`);
                    if (checkbox) {
                        checkbox.checked = false;
                        updateSelectedItems();
                    }
                }
            });
        }


        const registerStockCheckBtn = document.getElementById('registerStockCheckBtn');
        if (registerStockCheckBtn) {
            registerStockCheckBtn.addEventListener('click', function (e) {
                e.preventDefault();
                console.log('실사 등록 버튼 클릭'); // 디버깅용 로그

                const selectedRows = document.querySelectorAll('#selectedItemsTableBody tr');
                const stockChecks = Array.from(selectedRows).map(row => {
                    const stockIdInput = row.querySelector('input[name="stockId"]');
                    const checkAmountInput = row.querySelector('input[name="checkAmount"]');

                    if (!stockIdInput || !checkAmountInput) {
                        console.error('필요한 입력 요소를 찾을 수 없습니다:', row);
                        return null;
                    }

                    return {
                        stockDTO: {
                            id: parseInt(stockIdInput.value)
                        },
                        checkAmount: parseInt(checkAmountInput.value)
                    };
                }).filter(item => item !== null);

                if (stockChecks.length === 0) {
                    alert('선택된 항목이 없거나 필요한 정보가 누락되었습니다.');
                    return;
                }


                console.log('전송할 데이터:', stockChecks); // 디버깅용 로그

                fetch('/stockcheck/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(stockChecks)
                })
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        }
                        throw new Error('서버 응답 오류');
                    })
                    .then(data => {
                        if (data.success) {
                            alert('실사 등록이 완료되었습니다.');
                            window.location.reload();
                        } else {
                            alert('실사 등록 중 오류가 발생했습니다: ' + (data.message || '알 수 없는 오류'));
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('실사 등록 중 오류가 발생했습니다: ' + error.message);
                    });
            });
        } else {
            console.error('registerStockCheckBtn not found');
        }


        // // 실사 수정, 삭제, 반영 버튼 이벤트
        // const actionButtons = document.querySelectorAll('#stockCheckForm button[type="submit"]');
        // actionButtons.forEach(button => {
        //     button.addEventListener('click', function (e) {
        //         e.preventDefault();
        //         const action = this.textContent.trim();
        //         switch (action) {
        //             case '실사 수정':
        //                 // 실사 수정 로직
        //                 break;
        //             case '실사 삭제':
        //                 // 실사 삭제 로직
        //                 break;
        //         }
        //     });
        // });

        // 임시재고 마감 버튼 이벤트
        const closeButton = document.getElementById('closeButton');
        if (closeButton) {
            closeButton.addEventListener('click', function () {
                if (confirm('정말로 마감하시겠습니까?')) {
                    fetch('/stock/update', {
                        method: 'GET',
                        headers: {
                            'X-Requested-With': 'XMLHttpRequest'
                        }
                    })
                        .then(response => {
                            if (response.ok) {
                                alert('마감이 완료되었습니다.');
                                window.location.reload();
                            } else {
                                alert('마감 처리 중 오류가 발생했습니다.');
                            }
                        })
                        .catch(error => {
                            console.error('Error:', error);
                            alert('마감 처리 중 오류가 발생했습니다.');
                        });
                }
            });
        }
    }





      // 페이지네이션 업데이트
      function updatePagination(pageData) {
        const pagination = document.querySelector('.pagination');
        if (!pagination) return;

        pagination.innerHTML = '';

        // 이전 페이지 버튼
        const prevButton = `
        <li class="page-item ${pageData.prev ? '' : 'disabled'}">
            <a class="page-link" href="#" onclick="loadPage(${pageData.start - 1}); return false;">이전</a>
        </li>
    `;
        pagination.insertAdjacentHTML('beforeend', prevButton);

        // 페이지 번호 버튼
        for (let i = pageData.start; i <= pageData.end; i++) {
            const pageButton = `
            <li class="page-item ${pageData.page === i ? 'active' : ''}">
                <a class="page-link" href="#" onclick="loadPage(${i}); return false;">${i}</a>
            </li>
        `;
            pagination.insertAdjacentHTML('beforeend', pageButton);
        }

        // 다음 페이지 버튼
        const nextButton = `
        <li class="page-item ${pageData.next ? '' : 'disabled'}">
            <a class="page-link" href="#" onclick="loadPage(${pageData.end + 1}); return false;">다음</a>
        </li>
    `;
        pagination.insertAdjacentHTML('beforeend', nextButton);
    }

    // 페이지 로드 함수
    function loadPage(page) {
        fetchStockList(`/stockcheck/list?page=${page}`);
    }

    // 전역 스코프에 loadPage 함수 추가
    window.loadPage = loadPage;



});

