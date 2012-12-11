package ihm;

/**
 * Couple d'objets
 * @author vernagaa
 */
public class Couple<K,V> {

	private K key;
	private V value;

	/**
	 * Constructeur du couple
	 * @param val1 Objet clé
	 * @param val2 Objet valeur
	 */
	public Couple(K val1, V val2) {
		this.key = val1;
		this.value = val2;
	}

	/**
	 * Getter de l'objet clé
	 * @return 
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Setter de l'objet clé
	 * @param key 
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * Getter de l'objet valeur
	 * @return 
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Setter de l'objet valeur
	 * @param value 
	 */
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
}
