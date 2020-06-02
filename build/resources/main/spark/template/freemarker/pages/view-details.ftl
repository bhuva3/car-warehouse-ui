
<div class="container">
  <a class="btn btn-link" href="/open/home">Back to Car list</a>
  <h2>${(warehouse.cars.vehicles[0].make)!} : ${(warehouse.cars.vehicles[0].model)!}</h2>

  <table class="table table-hover">
    <thead>
      <tr>
        <th>Brand </th>
        <th>Model </th>
        <th>Price </th>
        <th>Licensed</th>
        <th>Added date</th>
        <th>Car location</th>
        <th>Warehouse name</th>
        <th>Add to Cart</th>
      </tr>
    </thead>
    <tbody>
    <#setting locale="en_US">
    <#if warehouse??>
      <tr>
        <td>${(warehouse.cars.vehicles[0].make)!}</td>
        <td>${(warehouse.cars.vehicles[0].model)!}</td>
        <td>${(warehouse.cars.vehicles[0].price?string.currency)!}</td>
        <td>${(warehouse.cars.vehicles[0].licensed?string("yes", "no"))!}</td>
        <td>${(warehouse.cars.vehicles[0].dateAdded)!}</td>
        <td>${(warehouse.cars.carLocation)!}</td>
        <td>${(warehouse.name)!}</td>
        <td>
        <form name="frmViewDetails" id="frmViewDetails" action="/open/addtocart" method="post">
            <input type="hidden" id="vehicleId" name="vehicleId" value="${(warehouse.cars.vehicles[0].id)!}" />
            <input type="hidden" id="csrfToken" name="csrfToken" value="${(sessionData.csrfToken)!?html}" />
            <input type="hidden" id="vehicleMake" name="vehicleMake" value="${(warehouse.cars.vehicles[0].make)!}" />
            <input type="hidden" id="vehicleModel" name="vehicleModel" value="${(warehouse.cars.vehicles[0].model)!}" />
            <input type="hidden" id="vehiclePrice" name="vehiclePrice" value="${warehouse.cars.vehicles[0].price?string.computer!}" />
            <button type="submit" class="btn btn-default">Add to Cart</button>
        </form>
        </td>
      </tr>

      <#else>
            <tr>
                    <td colspan="7">No vehicle details found !!</td>
            </tr>
      </#if>
    </tbody>
  </table>
</div>
