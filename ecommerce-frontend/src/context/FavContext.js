import React, { createContext, useState } from 'react';

export const FavContext = createContext();

export function FavProvider({ children }) {
  const [favorites, setFavorites] = useState([]);

  const addToFavorites = (product) => {
    setFavorites([...favorites, product]);
  };

  const removeFromFavorites = (productId) => {
    setFavorites(favorites.filter(product => product.id !== productId));
  };

  return (
    <FavContext.Provider value={{ favorites, addToFavorites, removeFromFavorites }}>
      {children}
    </FavContext.Provider>
  );
}
