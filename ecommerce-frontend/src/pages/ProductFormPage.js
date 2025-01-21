import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

function ProductFormPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState({ name: '', description: '', price: '' });

  useEffect(() => {
    if (id) {
      axios.get(`/api/products/${id}`).then((response) => {
        setProduct(response.data);
      });
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (id) {
      axios.put(`/api/products/${id}`, product).then(() => navigate('/'));
    } else {
      axios.post('/api/products', product).then(() => navigate('/'));
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input type="text" name="name" value={product.name} onChange={handleChange} required />
      </label>
      <label>
        Description:
        <textarea name="description" value={product.description} onChange={handleChange} required />
      </label>
      <label>
        Price:
        <input type="number" name="price" value={product.price} onChange={handleChange} required />
      </label>
      <button type="submit">Save</button>
    </form>
  );
}

export default ProductFormPage;
