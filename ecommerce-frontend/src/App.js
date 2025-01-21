import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import HomePage from './pages/HomePage';
import ProductPage from './pages/ProductPage';
import CartPage from './pages/CartPage';
import FavPage from './pages/FavPage';
import ProductFormPage from './pages/ProductFormPage';
import { CartProvider } from './context/CartContext';
import { FavProvider } from './context/FavContext';

function App() {
  return (
    <Router>
      <CartProvider>
        <FavProvider>
          <Header />
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/product/:id" element={<ProductPage />} />
            <Route path="/cart" element={<CartPage />} />
            <Route path="/favorites" element={<FavPage />} />
            <Route path="/product/create" element={<ProductFormPage />} />
            <Route path="/product/edit/:id" element={<ProductFormPage />} />
          </Routes>
        </FavProvider>
      </CartProvider>
    </Router>
  );
}

export default App;
