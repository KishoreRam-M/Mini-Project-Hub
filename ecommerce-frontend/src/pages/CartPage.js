import React, { useContext } from 'react';
import { CartContext } from '../context/CartContext';

function CartPage() {
  const { cart, removeFromCart } = useContext(CartContext);

  return (
    <div>
      <h1>Shopping Cart</h1>
      {cart.length === 0 ? <p>Your cart is empty</p> : (
        <div>
          {cart.map((product) => (
            <div key={product.id}>
              <p>{product.name} - ${product.price}</p>
              <button onClick={() => removeFromCart(product.id)}>Remove</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default CartPage;
