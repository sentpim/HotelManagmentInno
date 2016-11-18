<#include "template.ftl"/>
<#macro m_body>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">${hotel.name} - Rooms</h4>
                        <div class="text-center">
                            <a href="/hotels/${hotel.id?c}/rooms/add">
                                <button type="submit" class="btn btn-info btn-fill btn-wd">Add new</button>
                            </a>
                        </div>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-striped">
                            <thead>
                                <th>ID</th>
                                <th>Number</th>
                                <th>Room type</th>
                            </thead>
                            <tbody>
                                <#list rooms as room>
                                    <tr>
                                        <td>${room.id}</td>
                                        <td>${room.number}</td>
                                        <td>${room.getRoomType().name}</td>
                                    </tr>
                                </#list>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</#macro>
