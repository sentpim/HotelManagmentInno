<#include "template.ftl"/>
<#macro m_body>
    
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-4 col-sm-6">
                    <div class="card">
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-5">
                                    <div class="icon-big icon-warning text-center">
                                        <i class="ti-server"></i>
                                    </div>
                                </div>
                                <div class="col-xs-7">
                                    <div class="numbers">
                                        <p>Capacity</p>
                                        ${hotel.getCapacity()}
                                    </div>
                                </div>
                            </div>
                            <div class="footer">
                                <hr />
                                <div class="stats">
                                    <i class="ti-reload"></i> Updated now
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <div class="card">
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-5">
                                    <div class="icon-big icon-success text-center">
                                        <i class="ti-pulse"></i>
                                    </div>
                                </div>
                                <div class="col-xs-7">
                                    <div class="numbers">
                                        <p>Residents</p>
                                        ${hotel.getResidentsCount()}
                                    </div>
                                </div>
                            </div>
                            <div class="footer">
                                <hr />
                                <div class="stats">
                                    <i class="ti-calendar"></i> Last day
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-sm-6">
                    <div class="card">
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-5">
                                    <div class="icon-big icon-danger text-center">
                                        <i class="ti-wallet"></i>
                                    </div>
                                </div>
                                <div class="col-xs-7">
                                    <div class="numbers">
                                        <p>Budget</p>
                                        ${hotel.budget}$
                                    </div>
                                </div>
                            </div>
                            <div class="footer">
                                <hr />
                                <div class="stats">
                                    <i class="ti-timer"></i> In the last hour
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <#--<div class="col-lg-3 col-sm-6">-->
                    <#--<div class="card">-->
                        <#--<div class="content">-->
                            <#--<div class="row">-->
                                <#--<div class="col-xs-5">-->
                                    <#--<div class="icon-big icon-info text-center">-->
                                        <#--<i class="ti-twitter-alt"></i>-->
                                    <#--</div>-->
                                <#--</div>-->
                                <#--<div class="col-xs-7">-->
                                    <#--<div class="numbers">-->
                                        <#--<p>Followers</p>-->
                                        <#--2k-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="footer">-->
                                <#--<hr />-->
                                <#--<div class="stats">-->
                                    <#--<i class="ti-reload"></i> Updated now-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            </div>
            <div class="row">
                <h3>Reports: </h3>
                <#if reports?size == 0>
                    <div class="col-md-12">
                        <div class="card ">
                            <div class="header" style="padding-bottom: 20px;">
                                <h4 class="title" style="text-transform: none">There're no reports yet</h4>
                            </div>
                        </div>
                    </div>
                </#if>
                <#list reports as report>
                    <div class="col-md-12">
                        <div class="card ">
                            <div class="header">
                                <h4 class="title" style="text-transform: none">${report.getPerson().firstName} ${report.getPerson().lastName}</h4>
                            </div>
                            <div class="content">
                                <div class="footer">
                                    <div class="chart-legend">
                                        ${report.text}
                                    </div>
                                    <hr>
                                    <div class="stats">
                                        <a href="/report/${report.id?c}/delete"><i class="ti-close"></i> Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>

</#macro>

<script type="text/javascript">
    $(document).ready(function(){

        demo.initChartist();

        $.notify({
            message: "Welcome to <b>Zakirskikh Managment System</b>"

        },{
            type: 'success',
            timer: 4000
        });

    });
</script>
