
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
        <th>Purchase</th>
      </tr>
    </thead>
    <tbody>
    <h3 style="color:green">${message!}</h3>
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
        <td>
            <#if vehicle.licensed>
            <form name="frmViewDetails_${vehicle.id!}" id="frmViewDetails_${vehicle.id!}" action="/open/addtocart" method="post">
                <input type="hidden" id="csrfToken" name="csrfToken" value="${(sessionData.csrfToken)!?html}" />
                <input type="hidden" id="vehicleId" name="vehicleId" value="${vehicle.id!}" />
                <input type="hidden" id="vehicleMake" name="vehicleMake" value="${vehicle.make!}" />
                <input type="hidden" id="vehicleModel" name="vehicleModel" value="${vehicle.model!}" />
                <input type="hidden" id="vehiclePrice" name="vehiclePrice" value="${vehicle.price?string.computer!}" />
                <button type="submit" class="btn btn-default">Add to Cart</button>
            </form>
            </#if>
        </td>
      </tr>
      </#list>

      <#else>
            <tr>
                    <td colspan="3" >No vehicle record found !!</td>
            </tr>
      </#if>
    </tbody>
  </table>
</div>
