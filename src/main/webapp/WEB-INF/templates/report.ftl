<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>

<#include "template.ftl"/>
<#macro m_body>

<div class="content">
    <div class="container-fluid">
        <@form.form action="/report/add" id="form_report_add" method="post" modelAttribute="reportForm">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>Send a report</label>
                        <@form.textarea path="text"rows="13" class="form-control border-input" placeholder="Here can be your description" value=""></@form.textarea>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-info btn-fill btn-wd">Send</button>
                    </div>
                </div>
            </div>
        </@form.form>
    </div>
</div>
</#macro>