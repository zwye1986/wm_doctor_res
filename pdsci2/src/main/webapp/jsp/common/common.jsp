<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 2025/3/5
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <style>
        .item{
            font-size: 14px;
            color: #0aa1ca;
            font-weight: bold ;
        }

        /* 整体容器样式 */
        /*#dataModal {
            border: 1px solid #ccc;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }*/

        /* 标题样式 */
        h2.data-table {
            margin-top: 0;
            color: #333;
            border-bottom: 1px solid #ccc;
            padding-bottom: 10px;
        }

        /* 表格样式 */
        table.data-table  {
            border-collapse: collapse;
            width: 100%;
            margin-top: 10px;
            text-align: center;
            border: 1px solid #e0e0e0;
        }

        .data-table th,.data-table td{
            border: 1px solid #e0e0e0;
        }

        .data-table th{
            padding: 10px 8px;
            text-align: center;
            font-size: 14px;
            background-color: #f5f5f5;
            color: #333;
            font-weight: 500;
        }
        .data-table td{
            padding: 10px 8px;
            font-size: 14px;
            text-align: center;
        }

        /* 导出按钮样式 */
        #exportBtn {
            float: right;
            padding: 6px 12px;
            border: 1px solid #007bff;
            border-radius: 3px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        #exportBtn:hover {
            background-color: #0056b3;
        }

        /* 分页区域样式 */
        div.data-table {
            margin-top: 10px;
        }

        /* 分页导航样式 */
        .ui-dialog #pageNumbers a {
            display: inline-block;
            padding: 4px 8px;
            margin: 0 3px;
            border: 1px solid #ccc;
            border-radius: 3px;
            text-decoration: none;
            color: #333;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .ui-dialog #pageNumbers a.active,
        .ui-dialog #pageNumbers a:hover {
            background-color: #54B2E5;
            color: #fff;
        }

        /* 上一页和下一页按钮样式 */
        .ui-dialog #prevPage,
        .ui-dialog #nextPage {
            display: inline-block;
            padding: 4px 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
            text-decoration: none;
            color: #333;
            margin: 0 3px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .ui-dialog #prevPage:hover,
        .ui-dialog #nextPage:hover {
            background-color: #f5f5f5;
        }

        /* 跳转到页码输入框样式 */
        .ui-dialog #jumpPage {
            padding: 4px 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
            width: 50px;
        }

        /* 每页显示条数选择框样式 */
        .ui-dialog ßß#pageSizeSelect {
            padding: 4px 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
    </style>
    <script>

        function renderTable() {
            debugger;
            const startIndex = (currentPage - 1) * pageSize;
            const endIndex = startIndex + pageSize;
            const paginatedData = data.slice(startIndex, endIndex);

            dataTable.innerHTML = '<thead><tr><th>轮转科室</th><th>轮转起止时间</th><th>项目类别</th><th>异常填写内容</th></tr></thead><tbody>';
            paginatedData.forEach(item => {
                const row = document.createElement('tr');
                row.innerHTML = `
          <td>`+item["轮转科室"]+`</td>
          <td>`+item["轮转起止时间"]+`</td>
          <td>`+item["项目类别"]+`</td>
          <td>`+item["异常填写内容"]+`</td>
        `;
                dataTable.querySelector('tbody').appendChild(row);
            });
            dataTable.innerHTML += '</tbody>';

            totalCountSpan.textContent = `共`+data.length+`条`;
            var content = $(".ui-dialog-content");
            if(content.length) {
                content.html($("#innerContent").html());
            }
            renderPageNumbers();
        }

        function renderPageNumbers() {
            var content = $(".ui-dialog-content");
            if(content.length) {
                totalCountSpan =  $('.ui-dialog #totalCount')[0];
                pageSizeSelect =  $('.ui-dialog #pageSizeSelect')[0];
                prevPage =  $('.ui-dialog #prevPage')[0];
                nextPage =  $('.ui-dialog #nextPage')[0];
                jumpPage =  $('.ui-dialog #jumpPage')[0];
                pageNumbers =  $('.ui-dialog #pageNumbers')[0];
            }
            const totalPages = Math.ceil(data.length / pageSize);
            pageNumbers.innerHTML = '';
            for (let i = 1; i <= totalPages; i++) {
                const pageLink = document.createElement('a');
                pageLink.href = 'javascript:void(0)';
                pageLink.textContent = i;
                if (i === currentPage) {
                    pageLink.classList.add('active');
                }
                pageLink.addEventListener('click', (e) => {
                    e.preventDefault();
                    currentPage = parseInt(pageLink.textContent);
                    renderTable();
                });
                pageNumbers.appendChild(pageLink);
                if (i < totalPages) {
                    pageNumbers.appendChild(document.createTextNode(' '));
                }
            }

            prevPage.addEventListener('click', (e) => {
                e.preventDefault();
                if (currentPage > 1) {
                    currentPage--;
                    renderTable();
                }
            });

            nextPage.addEventListener('click', (e) => {
                console.log(111)
                e.preventDefault();
                const totalPages = Math.ceil(data.length / pageSize);
                if (currentPage < totalPages) {
                    currentPage++;
                    renderTable();
                }
            });

            jumpPage.addEventListener('input', () => {
                const inputPage = parseInt(jumpPage.value);
                const totalPages = Math.ceil(data.length / pageSize);
                if (!isNaN(inputPage) && inputPage >= 1 && inputPage <= totalPages) {
                    currentPage = inputPage;
                    renderTable();
                }
            });

            pageSizeSelect.addEventListener('change', () => {
                pageSize = parseInt(pageSizeSelect.value);
                currentPage = 1;
                renderTable();
                pageSizeSelect.value = pageSize;
            });
        }




        // renderTable();

        var map = {};
        <c:if test="${not empty nonComplianceRecordsMap}">
        <c:forEach items="${nonComplianceRecordsMap}" var="item">
        map["${item.key}"] = [];
        <c:forEach items="${item.value}" var="item2">
        var val = {};
        map["${item.key}"].push(val);
        <%--val['orgName'] = "${item2.orgName}";--%>
        val['轮转科室'] = "${item2.schDeptName}";
        val['轮转起止时间'] = "${item2.startDate}"+"至"+"${item2.endDate}";
        val['项目类别'] = "${item2.checkItemName}";
        val['异常填写内容'] = `${item2.invalidContent}`;
        // val['schTime'] =
        <%--val['recFlow'] = "${item2.recFlow}";--%>
        </c:forEach>
        </c:forEach>
        </c:if>
        data = [];
        function showNonComplianceRecords(doctorFlow) {
            debugger;
            data = map[doctorFlow];
            // var content = "";
            // for(var i=0;i<list.length;i++){
            //     content += "在<span class='item'>"+list[i].orgName+"</span><span class='item'>"+list[i].schDeptName+"</span>轮转时<span class='item'>"+list[i].recTypeName+"</span>中的<span class='item'>"+list[i].checkItemName+"</span>的填写的是：";
            //     if(list[i].invalidContent === "" || list[i].invalidContent === null || list[i].invalidContent === undefined){
            //         content += "<span class='item'>空</span>";
            //     }else{
            //         content += "<span class='item'>"+list[i].invalidContent+"</span>";
            //     }
            //
            //     content += ",不合规范！记录ID="+list[i].recFlow+"<br>";
            // }
            dataTable = document.getElementById('dataTable');
            totalCountSpan = document.getElementById('totalCount');
            pageSizeSelect = document.getElementById('pageSizeSelect');
            prevPage = document.getElementById('prevPage');
            nextPage = document.getElementById('nextPage');
            jumpPage = document.getElementById('jumpPage');
            pageNumbers = document.getElementById('pageNumbers');
            currentPage = 1;
            pageSize = 10;
            renderTable();
            jboxOpenContent($("#innerContent").html(), '异常数据', 1000, 600, true);
            const totalPages = Math.ceil(data.length / pageSize);

            $('.ui-dialog #nextPage').click(()=>{
                if (currentPage < totalPages) {
                    currentPage++;
                    renderTable();
                }
            });

            $('.ui-dialog #pageNumbers').click(()=>{
                if (currentPage < totalPages) {
                    currentPage++;
                    renderTable();
                }
            });

            $('.ui-dialog #jumpPage').click(()=>{
                if (currentPage < totalPages) {
                    currentPage++;
                    renderTable();
                }
            });

            $('.ui-dialog #pageSizeSelect').click(()=>{
                if (currentPage < totalPages) {
                    currentPage++;
                    renderTable();
                }
            });

        }
    </script>
</head>
<body>

<div id="innerContent" style="display: none">
    <div id="dataModal">
        <%--<div>
            <span>异常填报项目及内容</span>
        </div>--%>
        <table id="dataTable" class="data-table" >
            <thead class="data-table">
            <tr class="data-table">
                <th class="data-table">轮转科室</th>
                <th class="data-table">轮转起止时间</th>
                <th class="data-table">项目类别</th>
                <th class="data-table">异常填写内容</th>
            </tr>
            </thead>
            <tbody>
            <!-- 这里的数据将通过JavaScript动态填充 -->
            </tbody>
        </table>
        <div style="text-align: right; margin-top: 20px">
            <span id="totalCount">共10条</span>
            <select id="pageSizeSelect">
                <option value="10">10条/页</option>
                <option value="20">20条/页</option>
                <option value="30">30条/页</option>
                <option value="40">40条/页</option>
                <option value="50">50条/页</option>
            </select>
            <a href="javascript:void(0)" id="prevPage" class="prevPage"> < </a>
            <span id="pageNumbers"  class="pageNumbers"></span>
            <a href="javascript:void(0)" id="nextPage" class="nextPage"> > </a>
            <input type="number" id="jumpPage" placeholder="跳至">
            <span>页</span>
        </div>
    </div>

    <script>

    </script>
</div>

</body>
</html>
