<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>


<script>
    $(function () {
        $("ul.pagination li.disabled a").click(function () {
            return false;
        });
    });

</script>


<nav>
    <ul class="pagination">
        <%--  首先，分页超链的效果，用的Bootstrap的分页效果来制作
              首页超链：--%>
        <%--如果page.hasPreviouse == 0 ，则表示是第一页，disable 无法点击--%>
        <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
            <a href="?start=0${page.param}" aria-label="Previous">
                <%--图标按钮--%>
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <%--上一页超链：--%>
        <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
            <a href="?start=${page.start-page.count}${page.param}" aria-label="Previous">
                <span aria-hidden="true">&lsaquo;</span>
            </a>
        </li>

        <%--中间页--%>
        <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
            <%--没有数据的时候不显示数字--%>
            <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
                <a
                        href="?start=${status.index*page.count}${page.param}"
                        <c:if test="${status.index*page.count==page.start}">class="current"</c:if>
                >${status.count}</a>
            </li>

        </c:forEach>

        <%--下一页超链--%>
        <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
            <a href="?start=${page.start+page.count}${page.param}" aria-label="Next">
                <span aria-hidden="true">&rsaquo;</span>
            </a>
        </li>

        <%--最后一页--%>
        <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
            <a href="?start=${page.last}${page.param}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
