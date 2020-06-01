
<div class="container">
  <h2>Cars on Sale</h2>
  <p>Click on name to see the details</p>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Brand </th>
        <th>Model </th>
        <th>Price </th>
        <th>Licensed</th>
        <th>Added date</th>
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
