from flask import Flask, request, jsonify
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image
import numpy as np
import os

app = Flask(__name__)

# Load the pre-trained model
model = load_model('gymvision_modelV2.h5')  # Ganti dengan nama dan path model Anda

# Function to handle file upload and perform prediction
def handle_upload_and_predict(uploaded_file):
    img = image.load_img(uploaded_file, target_size=(299, 299))
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    img_array /= 255.

    # Perform prediction
    prediction = model.predict(img_array)

    # Convert prediction to class name
    class_names = ['Elliptical Machine', 'Home Machine', 'Recumbent Bike',
                   'Benchpress', 'dumbell', 'kettleBell', 'PullBar', 'treadMill']
    predicted_class = class_names[np.argmax(prediction)]

    return predicted_class

@app.route('/predict', methods=['POST'])
def predict():
    if 'file' not in request.files:
        return jsonify({'error': 'No file part in the request'}), 400

    file = request.files['file']

    if file.filename == '':
        return jsonify({'error': 'No selected file'}), 400

    if file:
        file_path = os.path.join('/tmp', file.filename)
        file.save(file_path)
        
        predicted_class = handle_upload_and_predict(file_path)
        os.remove(file_path)  # Clean up the file after prediction
        
        return jsonify({'hasil_prediksi': predicted_class})

if __name__ == '__main__':
    app.run(debug=True, port=8000)
