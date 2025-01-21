import React, { useContext } from 'react';
import { FavContext } from '../context/FavContext';

function FavPage() {
  const { favorites, removeFromFavorites } = useContext(FavContext);

  return (
    <div>
      <h1>Favorites</h1>
      {favorites.length === 0 ? <p>No favorites yet</p> : (
        <div>
          {favorites.map((product) => (
            <div key={product.id}>
              <p>{product.name}</p>
              <button onClick={() => removeFromFavorites(product.id)}>Remove</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default FavPage;
