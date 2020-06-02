
<div class="container">
  <a class="btn btn-link" href="/open/home">Back to Car list</a>
  <h2>Your Cart Details</h2>

  <table class="table table-hover">
    <thead>
      <tr>
        <th>Brand </th>
        <th>Model </th>
        <th>Price </th>
      </tr>
    </thead>
    <tbody>
    <#setting locale="en_US">
    <#if sessionData?? && sessionData.cartDetailList?? && sessionData.cartDetailList?size gt 0>
      <#list sessionData.cartDetailList as carDetails>
      <tr>
        <td>${(carDetails.vehicleMake)!}</td>
        <td>${(carDetails.vehicleModel)!}</td>
        <td>${(carDetails.vehiclePrice?string.currency)!}</td>

      </tr>
       </#list>
       <tr>
           <td></td>
           <td><b>Cart Total </b></td>
           <td>${(cartTotal?string.currency)!}</td>

       </tr>
      <#else>
            <tr>
                    <td colspan="7">Your cart is empty !!</td>
            </tr>
      </#if>
    </tbody>
  </table>
</div>
