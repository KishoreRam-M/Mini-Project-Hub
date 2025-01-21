import React, { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { CartContext } from '../context/CartContext';
import { FavContext } from '../context/FavContext';

function HomePage() {
  const [products, setProducts] = useState([]);
  const { addToCart } = useContext(CartContext);
  const { addToFavorites } = useContext(FavContext);

  useEffect(() => {
    axios.get('/api/products').then((response) => {
      setProducts(response.data);
    });
  }, []);

  return (
    <div>
      <h1>Products</h1>
      <div>
        {products.map((product) => (
          <div key={product.id}>
            <Link to={`/product/${product.id}`}>{product.name}</Link>
            <button onClick={() => addToCart(product)}>Add to Cart</button>
            <button onClick={() => addToFavorites(product)}>Add to Favorites</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default HomePage;
