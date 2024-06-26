<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>

    <script type="application/javascript">
        //学员查看证明
        function certInfo(flag){
            jboxOpen("<s:url value='/xjgl/user/certificateDetail?roleFlag=student&certificateType='/>"+flag+"&userFlow=${userFlow}","证书信息",560,600);
        }
    </script>
</head>
<body>
    <div class="mainright">
        <div class="content">
            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                <tr>
                    <th colspan="6" style="text-align: left;padding-left: 20px;">基本信息</th>
                </tr>
                <tr>
                    <th style="text-align: right;width:15%">学号：</th>
                    <td style="width:18%"><label>${eduUser.sid}</label></td>
                    <th style="text-align: right;width:15%">姓名：</th>
                    <td style="width:18%"><label>${sysUser.userName }</label></td>
                    <th style="text-align: right;width:15%">性别：</th>
                    <td style="width:18%"><label>${sysUser.sexName }</label></td>
                </tr>
                <tr>
                    <th style="text-align: right;">姓名拼音：</th>
                    <td><label>${eduUser.nameSpell}</label></td>
                    <th style="text-align: right;">证件类型：</th>
                    <td><label>${sysUser.cretTypeName}</label></td>
                    <th style="text-align: right;">证件号码：</th>
                    <td><label>${sysUser.idNo}</label></td>
                </tr>
                <tr>
                    <th style="text-align: right;">民族：</th>
                    <td><label>
                        <c:forEach items="${userNationEnumList}" var="nation">
                            ${sysUser.nationId eq nation.id ? nation.name:''}
                        </c:forEach>
                    </label></td>
                    <th style="text-align: right;">班级：</th>
                    <td><label>${eduUser.className}</label></td>
                    <th style="text-align: right;">出生日期：</th>
                    <td><label>${sysUser.userBirthday}</label></td>
                </tr>
                <tr>
                    <th style="text-align: right;">入学年级：</th>
                    <td><label>${eduUser.period}</label></td>
                    <th style="text-align: right;">专业名称：</th>
                    <td><label>${eduUser.majorName}</label></td>
                    <th style="text-align: right;">培养层次：</th>
                    <td><label>${eduUser.trainTypeName}</label></td>
                </tr>
            </table>
            <div style="margin-top:20px;width:100%;height:200px;">
                <div style="padding:20px 40px">
                    <input type="button" class="search" onclick="certInfo('2')" value="毕业证明">
                    &#12288;&#12288;
                    <input type="button" class="search" onclick="certInfo('1')" value="学位证明">
                </div>
            </div>
        </div>
    </div>
</body>
</html>