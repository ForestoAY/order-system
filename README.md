# **Dokumentasi Respons API**

---

## **Produk**

### **1\. Tambah Produk (POST /api/products)**

#### **Request Body:**

```
{
    "name": "Laptop",
    "typeId": 1,
    "price": 1500.0
}
```

#### **Respon:**

```
{
    "id": 1,
    "name": "Laptop",
    "type": {
        "id": 1,
        "name": "Electronic"
    },
    "price": 1500.0
}
```

---

### **2\. Perbarui Produk (PUT /api/products/{id})**

#### **Request Body:**

```
{
    "name": "Smartphone",
    "typeId": 1,
    "price": 1000.0
}
```

#### **Respon:**

```
{
    "message": "Product with ID 1 has been updated."
}
```

---

### **3\. Hapus Produk (DELETE /api/products/{id})**

#### **Respon:**

```
{
    "message": "Product with ID 1 has been deleted."
}
```

---

### **4\. Lihat Daftar Produk (GET /api/products)**

#### **Query Parameters:**

- `page`: Halaman (default: 0)
- `size`: Jumlah item per halaman (default: 10)

#### **Respon:**

```
{
    "content": [
        {
            "id": 1,
            "name": "Laptop",
            "type": {
                "id": 1,
                "name": "Electronic"
            },
            "price": 1500.0
        }
    ],
    "totalPages": 1,
    "totalElements": 1
}
```

---

### **5\. Lihat Produk Berdasarkan ID (GET /api/products/{id})**

#### **Respon:**

```
{
    "id": 1,
    "name": "Laptop",
    "type": {
        "id": 1,
        "name": "Electronic"
    },
    "price": 1500.0
}
```

---

## **Keranjang**

### **1\. Tambah ke Keranjang (POST /api/order/add-to-cart)**

#### **Query Parameters:**

- `productId`: ID produk.
- `quantity`: Jumlah produk.

#### **Respon:**

```
{
    "id": 1,
    "isPlaced": false,
    "items": [
        {
            "product": {
                "id": 1,
                "name": "Laptop",
                "type": {
                    "id": 1,
                    "name": "Electronic"
                },
                "price": 1500.0
            },
            "quantity": 2
        }
    ],
    "totalAmount": 3000.0
}
```

---

### **2\. Selesaikan Keranjang (POST /api/order/place-order)**

#### **Respon:**

```
{
    "id": 1,
    "isPlaced": true,
    "items": [
        {
            "product": {
                "id": 1,
                "name": "Laptop",
                "type": {
                    "id": 1,
                    "name": "Electronic"
                },
                "price": 1500.0
            },
            "quantity": 1
        }
    ],
    "totalAmount": 1500.0
}
```

---

### **3\. Lihat Semua Keranjang (GET /api/order/cart)**

#### **Respon:**

```
[
    {
        "id": 1,
        "isPlaced": true,
        "items": [
            {
                "product": {
                    "id": 1,
                    "name": "Laptop",
                    "type": {
                        "id": 1,
                        "name": "Electronic"
                    },
                    "price": 1500.0
                },
                "quantity": 1
            }
        ],
        "totalAmount": 1500.0
    },
    {
        "id": 2,
        "isPlaced": false,
        "items": [
            {
                "product": {
                    "id": 2,
                    "name": "Smartphone",
                    "type": {
                        "id": 1,
                        "name": "Electronic"
                    },
                    "price": 1000.0
                },
                "quantity": 2
            }
        ],
        "totalAmount": 2000.0
    }
]
```
