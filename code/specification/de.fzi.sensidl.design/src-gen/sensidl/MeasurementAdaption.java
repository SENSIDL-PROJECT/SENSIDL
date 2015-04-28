/**
 */
package sensidl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement Adaption</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sensidl.MeasurementAdaption#getScalingFactor <em>Scaling Factor</em>}</li>
 *   <li>{@link sensidl.MeasurementAdaption#getOffset <em>Offset</em>}</li>
 * </ul>
 * </p>
 *
 * @see sensidl.SensidlPackage#getMeasurementAdaption()
 * @model
 * @generated
 */
public interface MeasurementAdaption extends MeasurementConstraint {
	/**
	 * Returns the value of the '<em><b>Scaling Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scaling Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scaling Factor</em>' attribute.
	 * @see #setScalingFactor(Double)
	 * @see sensidl.SensidlPackage#getMeasurementAdaption_ScalingFactor()
	 * @model required="true"
	 * @generated
	 */
	Double getScalingFactor();

	/**
	 * Sets the value of the '{@link sensidl.MeasurementAdaption#getScalingFactor <em>Scaling Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scaling Factor</em>' attribute.
	 * @see #getScalingFactor()
	 * @generated
	 */
	void setScalingFactor(Double value);

	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offset</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(Double)
	 * @see sensidl.SensidlPackage#getMeasurementAdaption_Offset()
	 * @model required="true"
	 * @generated
	 */
	Double getOffset();

	/**
	 * Sets the value of the '{@link sensidl.MeasurementAdaption#getOffset <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(Double value);

} // MeasurementAdaption
