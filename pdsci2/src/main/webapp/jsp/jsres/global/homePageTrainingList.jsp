<div class="search_table" style="height: 565px;overflow-y: auto">
	<input type="hidden" id="DataType" value="${tabId}">
	<c:if test="${tabId eq 'orgType'}">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<colgroup>
				<col width="25%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
			</colgroup>
			<tr>
				<th>基地名称</th>
				<th>本单位人</th>
				<th>委培单位人</th>
				<th>社会人</th>
				<th>在校专硕 </th>
				<th>合计</th>
			</tr>
			<c:set var="CompanyNum" value="0"></c:set>
			<c:set var="CompanyEntrustNum" value="0"></c:set>
			<c:set var="SocialNum" value="0"></c:set>
			<c:set var="GraduateNum" value="0"></c:set>

			<c:forEach items="${orgList}" var="org">
				<tr>
					<td>${org.orgName}</td>
					<td>
						<c:set var="rowNum" value="0"></c:set>
						<c:set var="dataInfo" value="${org.orgFlow}${'Company'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="CompanyNum" value="${CompanyNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${org.orgFlow}${'CompanyEntrust'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="CompanyEntrustNum" value="${CompanyEntrustNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${org.orgFlow}${'Social'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="SocialNum" value="${SocialNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${org.orgFlow}${'Graduate'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="GraduateNum" value="${GraduateNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
							${rowNum}
					</td>
				</tr>
			</c:forEach>
			<c:forEach items="${orgMapList}" var="org">
				<tr>
					<td>
							${org.orgName}
					</td>
					<td>
						<c:set var="rowNum" value="0"></c:set>
						<c:set var="dataInfo" value="${org.orgFlow}${'Company'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="CompanyNum" value="${CompanyNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${org.orgFlow}${'CompanyEntrust'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="CompanyEntrustNum" value="${CompanyEntrustNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${org.orgFlow}${'Social'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="SocialNum" value="${SocialNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${org.orgFlow}${'Graduate'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="GraduateNum" value="${GraduateNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
							${rowNum}
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>合计</td>
				<td>${CompanyNum}</td>
				<td>${CompanyEntrustNum}</td>
				<td>${SocialNum}</td>
				<td>${GraduateNum}</td>
				<td>${CompanyNum+CompanyEntrustNum+SocialNum+GraduateNum}</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${tabId eq 'speType'}">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<colgroup>
				<col width="25%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
			</colgroup>
			<tr>
				<th>地市名称</th>
				<th>本单位人</th>
				<th>委培单位人</th>
				<th>社会人</th>
				<th>在校专硕 </th>
				<th>合计</th>
			</tr>
			<c:set var="CompanyNum" value="0"></c:set>
			<c:set var="CompanyEntrustNum" value="0"></c:set>
			<c:set var="SocialNum" value="0"></c:set>
			<c:set var="GraduateNum" value="0"></c:set>


			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<tr>
					<td>${dict.dictName}</td>
					<td>
						<c:set var="rowNum" value="0"></c:set>
						<c:set var="dataInfo" value="${dict.dictId}${'Company'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="CompanyNum" value="${CompanyNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${dict.dictId}${'CompanyEntrust'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="CompanyEntrustNum" value="${CompanyEntrustNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${dict.dictId}${'Social'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="SocialNum" value="${SocialNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
						<c:set var="dataInfo" value="${dict.dictId}${'Graduate'}"></c:set>
						<c:set var="thisNum" value="${empty resultMap[dataInfo]?0:resultMap[dataInfo]}"></c:set>
						<c:set var="rowNum" value="${rowNum+thisNum}"></c:set>
						<c:set var="GraduateNum" value="${GraduateNum+thisNum}"></c:set>
							${thisNum}
					</td>
					<td>
							${rowNum}
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>合计</td>
				<td>${CompanyNum}</td>
				<td>${CompanyEntrustNum}</td>
				<td>${SocialNum}</td>
				<td>${GraduateNum}</td>
				<td>${CompanyNum+CompanyEntrustNum+SocialNum+GraduateNum}</td>
			</tr>
		</table>
	</c:if>

</div>