
### **1. Bernoulli(p):**

- **Fórmulas:**
    - \$ X_i \sim Bernoulli(p) \$, \$ \mathbb{E}[X_i] = p \$, \$ Var(X_i) = p(1-p) \$.
    - \$ S_n = \sum_{i=1}^n X_i \sim Binomial(n, p) \$, \$ \mathbb{E}[S_n] = np \$, \$ Var(S_n) = np(1-p) \$.
- **Exemplo:**
    - Suponha \$ p = 0.5 \$, \$ X_1 = \{0, 1, 0\}, X_2 = \{1, 0, 1\}, X_3 = \{0, 1, 1\} \$.
    - \$ S_3 = \{1, 2, 2\} \$.
    - Cálculos para \$ S_3 \$:
        - \$ \mathbb{E}[S_3] = 3 \times 0.5 = 1.5 \$
        - \$ Var(S_3) = 3 \times 0.5 \times (1-0.5) = 0.75 \$

---

### **2. Binomial(n, p):**

- **Fórmulas:**
    - \$ X_i \sim Bin(n_i, p) \$, \$ \mathbb{E}[X_i] = n_i p \$, \$ Var(X_i) = n_i p(1-p) \$.
    - \$ S_n = \sum_{i=1}^n X_i \sim Bin(\sum n_i, p) \$, \$ \mathbb{E}[S_n] = (\sum n_i)p \$, \$ Var(S_n) = (\sum n_i)p(1-p) \$.
- **Exemplo:**
    - Suponha \$ n_1 = 2, n_2 = 3, n_3 = 1 \$, \$ p = 0.5 \$, \$ X_1 = \{1, 0, 2\}, X_2 = \{2, 1, 1\}, X_3 = \{0, 2, 1\} \$.
    - \$ S_3 = \{3, 3, 4\} \$.
    - Cálculos para \$ S_3 \$:
        - \$ \mathbb{E}[S_3] = (2+3+1) \times 0.5 = 3 \$
        - \$ Var(S_3) = (2+3+1) \times 0.5 \times (1-0.5) = 1.5 \$

---

### **3. Poisson(λ):**

- **Fórmulas:**
    - \$ X_i \sim Poisson(\lambda) \$, \$ \mathbb{E}[X_i] = \lambda \$, \$ Var(X_i) = \lambda \$.
    - \$ S_n = \sum_{i=1}^n X_i \sim Poisson(n\lambda) \$, \$ \mathbb{E}[S_n] = n\lambda \$, \$ Var(S_n) = n\lambda \$.
- **Exemplo:**
    - Suponha \$ \lambda = 2 \$, \$ X_1 = \{2, 3, 1\}, X_2 = \{1, 0, 2\}, X_3 = \{0, 2, 1\} \$.
    - \$ S_3 = \{3, 5, 4\} \$.
    - Cálculos para \$ S_3 \$:
        - \$ \mathbb{E}[S_3] = 3 \times 2 = 6 \$
        - \$ Var(S_3) = 3 \times 2 = 6 \$

---

### **4. Geométrica(p):**

- **Fórmulas:**
    - \$ X_i \sim Geom(p) \$, \$ \mathbb{E}[X_i] = \frac{1}{p} \$, \$ Var(X_i) = \frac{1-p}{p^2} \$.
    - \$ S_n = \sum_{i=1}^n X_i \sim NegBin(n, p) \$, \$ \mathbb{E}[S_n] = \frac{n}{p} \$, \$ Var(S_n) = \frac{n(1-p)}{p^2} \$.
- **Exemplo:**
    - Suponha \$ p = 0.5 \$, \$ X_1 = \{3, 2, 5\}, X_2 = \{1, 4, 2\}, X_3 = \{2, 3, 1\} \$.
    - \$ S_3 = \{6, 9, 8\} \$.
    - Cálculos para \$ S_3 \$:
        - \$ \mathbb{E}[S_3] = \frac{3}{0.5} = 6 \$
        - \$ Var(S_3) = \frac{3(1-0.5)}{(0.5)^2} = 12 \$

---

### **5. Hipergeométrica(N, K, n):**

- **Fórmulas:**
    - \$ X_i \sim HG(N, K, n) \$, \$ \mathbb{E}[X_i] = n\frac{K}{N} \$, \$ Var(X_i) = n\frac{K}{N}\frac{N-K}{N}\frac{N-n}{N-1} \$.
    - \$ S_n \$: Não há forma fechada geral para a soma; pode-se usar convolução ou aproximações.
- **Exemplo:**
    - Suponha \$ N = 10, K = 5, n = 3 \$, \$ X_1 = \{1, 2, 1\}, X_2 = \{0, 1, 2\}, X_3 = \{2, 1, 0\} \$.
    - \$ S_3 = \{3, 4, 3\} \$.
    - Cálculos para \$ S_3 \$ não são diretos devido à falta de uma distribuição fechada para a soma.

---

### **6. Exponencial(λ):**

- **Fórmulas:**
    - \$ X_i \sim Exp(\lambda) \$, \$ \mathbb{E}[X_i] = \frac{1}{\lambda} \$, \$ Var(X_i) = \frac{1}{\lambda^2} \$.
    - \$ S_n \sim Gamma(n, \lambda) \$, \$ \mathbb{E}[S_n] = \frac{n}{\lambda} \$, \$ Var(S_n) = \frac{n}{\lambda^2} \$.
- **Exemplo:**
    - Suponha \$ \lambda = 1 \$, \$ X_1 = \{2.2, 1.6, 0.9\}, X_2 = \{0.7, 0.5, 0.4\}, X_3 = \{0.3, 0.2, 0.1\} \$.
    - \$ S_3 = \{3.2, 2.3, 1.4\} \$.
    - Cálculos para \$ S_3 \$:
        - \$ \mathbb{E}[S_3] = \frac{3}{1} = 3 \$
        - \$ Var(S_3) = \frac{3}{1^2} = 3 \$

---

### **7. Uniforme(a, b):**

- **Fórmulas:**
    - \$ X_i \sim \mathcal{U}(a, b) \$, \$ \mathbb{E}[X_i] = \frac{a+b}{2} \$, \$ Var(X_i) = \frac{(b-a)^2}{12} \$.
    - \$ S_n \sim Irwin-Hall(n) \$, \$ \mathbb{E}[S_n] = \frac{n(a+b)}{2} \$, \$ Var(S_n) = \frac{n(b-a)^2}{12} \$.
- **Exemplo:**
    - Suponha \$ a = 0, b = 1 \$, \$ X_1 = \{0.3, 0.5, 0.7\}, X_2 = \{0.1, 0.4, 0.9\}, X_3 = \{0.2, 0.6, 0.8\} \$.
    - \$ S_3 = \{0.6, 1.5, 2.4\} \$.
    - Cálculos para \$ S_3 \$:
        - \$ \mathbb{E}[S_3] = \frac{3(0+1)}{2} = 1.5 \$
        - \$ Var(S_3) = \frac{3(1-0)^2}{12} = 0.25 \$

---

### **8. Normal(μ, σ²):**

- **Fórmulas:**
    - \$ X_i \sim \mathcal{N}(\mu, \sigma^2) \$, \$ \mathbb{E}[X_i] = \mu \$, \$ Var(X_i) = \sigma^2 \$.
    - \$ S_n \sim \mathcal{N}(n\mu, n\sigma^2) \$, \$ \mathbb{E}[S_n] = n\mu \$, \$ Var(S_n) = n\sigma^2 \$.
- **Exemplo:**
    - Suponha \$ \mu = 0, \sigma^2 = 1 \$, \$ X_1 = \{1.2, -0.5, 2.1\}, X_2 = \{0.8, 1.5, -0.3\}, X_3 = \{-0.2, 0.7, 1.0\} \$.
    - \$ S_3 = \{1.8, 1.7, 2.8\} \$.
    - Cálculos para \$ S_3 \$:
        - \$ \mathbb{E}[S_3] = 3 \times 0 = 0 \$
        - \$ Var(S_3) = 3 \times 1 = 3 \$

---

### **9. Qui-Quadrado(k):**

- **Fórmulas:**
    - \$ X_i \sim \chi^2(k_i) \$, \$ \mathbb{E}[X_i] = k_i \$, \$ Var(X_i) = 2k_i \$.
    - \$ S_n \sim \chi^2(\sum k_i) \$, \$ \mathbb{E}[S_n] = \sum k_i \$, \$ Var(S_n) = 2\sum k_i \$.
- **Exemplo:**
    - Suponha \$ k_1 = 2, k_2 = 3, k_3 = 1 \$, \$ X_1 = \{2.5, 3.0, 1.8\}, X_2 = \{1.2, 2.7, 3.5\}, X_3 = \{0.9, 1.5, 2.3\} \$.
    - \$ S_3 = \{4.6, 7.2, 7.6\} \$.
    - Cálculos para \$ S_3 \$:
        - \$ \mathbb{E}[S_3] = 2 + 3 + 1 = 6 \$
        - \$ Var(S_3) = 2(2 + 3 + 1) = 12 \$

---

### **Legenda:**

- \$ \mathbb{E}[X_i] \$: Esperança de \$ X_i \$.
- \$ Var(X_i) \$: Variância de \$ X_i \$.
- \$ NegBin(r, p) \$: Binomial Negativa.
- \$ Irwin-Hall(n) \$: Distribuição da soma de \$ n \$ uniformes.
- \$ \chi^2(k) \$: Distribuição Qui-Quadrado com \$ k \$ graus de liberdade.

