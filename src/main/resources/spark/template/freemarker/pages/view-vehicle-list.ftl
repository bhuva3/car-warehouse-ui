
<div class="container">

  <h2>Cars on Sale</h2>
  <p>Click on View details link to see the details</p>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Brand </th>
        <th>Model </th>
        <th>Price </th>
        <th>Licensed</th>
        <th>Added date</th>
        <th>View details</th>
      </tr>
    </thead>
    <tbody>
    <#setting locale="en_US">
    <#if vehicleList?? && vehicleList?size gt 0>
    <#list vehicleList as vehicle>
      <tr>
        <td>${vehicle.make!}</td>
        <td>${vehicle.model!}</td>
        <td>${vehicle.price?string.currency!}</td>
        <td>${(vehicle.licensed?string("yes", "no"))!}</td>
        <td>${vehicle.dateAdded!}</td>
        <td>
            <#if vehicle.licensed>
            <form name="frmViewDetails_${vehicle.id!}" id="frmViewDetails_${vehicle.id!}" action="/open/viewdetails" method="post">
                <input type="hidden" id="csrfToken" name="csrfToken" value="${(sessionData.csrfToken)!?html}" />
                <input type="hidden" id="vehicleId" name="vehicleId" value="${vehicle.id!}" />
                <button type="submit" class="btn btn-link">View details</button>
            </form>
            </#if>
        </td>
      </tr>
      </#list>

      <#else>
            <tr>
                    <td colspan="3">No vehicle record found !!</td>
            </tr>
      </#if>
    </tbody>
  </table>
</div>
