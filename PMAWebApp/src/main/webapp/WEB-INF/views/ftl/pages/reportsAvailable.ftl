<#import "/spring.ftl" as spring />
<#assign serverDir>
    <@spring.url '/' />
</#assign>
<#function zebra index>
    <#if (index % 2)==0>
        <#return "even" />
        <#else>
            <#return "odd" />
    </#if>
</#function>
<style>
    .money {
        text-align: right;
    }

    td {
        padding: 5px;
    }

    tr.even {
        background-color: white;
    }

    tr.odd {
        background-color: #E6E9ED;
    }

    table {
        border-collapse: collapse;
        width: 100%;
    }

    table,
    th,
    td {
        border: 1px solid black;
    }

    table {}

    th {
        height: 50px;
        text-align: left;
        background-color: lightgray;
        color: white;
        padding-left: 10px;
    }

    td {
        /*    height: 50px; */
        vertical-align: bottom;
        padding: 8px;
    }
</style>
<div class="post" id="post-37">
    <ul class="children">
        <#if hasMoreThanOne>
            <li class="page_item page-item-159"><a href=<@spring.url "/reports/list" />>Account Summary</a></li>
        </#if>
        <li class="page_item page-item-159">
            <a href="<@spring.url "/reports/details/${acctNo?c}"/>">Account Details</a> </li> 
    </ul> <h2>Reports available for ${accountName}</h2>
    <div id="outsideTabs">
        <ul>
            <li><a href="#tab2019">Current</a></li>
            <li><a href="#tabs">Pre-2019</a></li>
        </ul>
        <div id="tab2019"><h2>2019 Data Here</h2>
        <table>
                                <tr>
                                    <th style="width:30%;">Date</th>
                                    <th style="width:50%;">Type</th>
                                    <th style="width:20%;">Link</th>
                                </tr>
                                    <tr >
                                        <td>March 2019</td>
                                        <td>Consolidated</td>
                                        <td><a href="" target="_blank">PDF File</a></td>
                                    </tr>
                                    <tr >
                                        <td>February 2019</td>
                                        <td>Consolidated</td>
                                        <td><a href="" target="_blank">PDF File</a></td>
                                    </tr>
                                    <tr >
                                        <td>January 2019</td>
                                        <td>Consolidated</td>
                                        <td><a href="" target="_blank">PDF File</a></td>
                                    </tr>
                            </table>
        </div>                    
        <div id="tabs">
            <ul>
                            <li><a href="#tabs-1">Monthly</a></li>
                            <li><a href="#tabs-2">Quarterly</a></li>
                            <li><a href="#tabs-3">Annual</a></li>
            </ul>
            <div id="tabs-1">
                            <table>
                                <tr>
                                    <th style="width:30%;">Date</th>
                                    <th style="width:50%;">Type</th>
                                    <th style="width:20%;">Link</th>
                                </tr>
                                <#list months as report>
                                    <tr class="${zebra(report?index)}">
                                        <td>${report.getReportDate()}</td>
                                        <td>${report.getReportTypeName()}</td>
                                        <td><a href="<@spring.url "/download-document/${acctNo?c}/${report.getReportId()?c}"/>"
                                                target="_blank">PDF File</a></td>
                                    </tr>
                                </#list>
                            </table>
            </div>
            <div id="tabs-2">
                            <table>
                                <tr>
                                    <th style="width:30%;">Date</th>
                                    <th style="width:50%;">Type</th>
                                    <th style="width:20%;">Link</th>
                                </tr>
                                <#list quarters as report>
                                    <tr class="${zebra(report?index)}">
                                        <td>${report.getReportDate()}</td>
                                        <td>${report.getReportTypeName()}</td>
                                        <td><a href="<@spring.url "/download-document/${acctNo?c}/${report.getReportId()?c}"/>"
                                                target="_blank">PDF File</a></td>
                                    </tr>
                                </#list>
                            </table>
            </div>
            <div id="tabs-3">
                            <table>
                                <tr>
                                    <th style="width:30%;">Date</th>
                                    <th style="width:50%;">Type</th>
                                    <th style="width:20%;">Link</th>
                                </tr>
                                <#list annuals as report>
                                    <tr class="${zebra(report?index)}">
                                        <td>${report.getReportDate()}</td>
                                        <td>${report.getReportTypeName()}</td>
                                        <td><a href="<@spring.url "/download-document/${acctNo?c}/${report.getReportId()?c}"/>"
                                                target="_blank">PDF File</a></td>
                                    </tr>
                                </#list>
                            </table>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#outsideTabs").tabs();
        $("#tabs").tabs();
    });
</script>