package com.phuongnt.studyquiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.phuongnt.studyquiz.AppConst;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.feedbackservice.FeedbackRequest;
import com.phuongnt.studyquiz.model.viewmodel.Question;
import com.phuongnt.studyquiz.model.viewmodel.User;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.utils.MyProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {
    private RadioGroup rdgErrors;
    private EditText edtComment;
    private Question question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            question = (Question) bundle.get(AppConst.SOURCE_OBJECT_KEY);
        }

        rdgErrors = (RadioGroup) findViewById(R.id.rdg_error_type);
        edtComment = (EditText) findViewById(R.id.edt_comment);

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.9);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.7);
        getWindow().setLayout(width, height);
    }

    public void onButtonCancelSelected(View v){
        onBackPressed();
    }

    public void onButtonFeedbackSelected(View v){
        String comment = edtComment.getText().toString();
        int selectedRadioId = rdgErrors.getCheckedRadioButtonId();
        RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioId);
        int index = rdgErrors.indexOfChild(selectedRadio) + 1;
        User user = User.getCurrentUser();
        if(user == null){
            return;
        }
        FeedbackRequest request = new FeedbackRequest(user.getUserId(), question.getValue().getQuestionId(), index, comment);
        sendFeedBack(request);
    }

    public void sendFeedBack(FeedbackRequest request){
        MyProgressBar.show(this);
        IAPIHelper iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
        Call<CommonResponse> call = iapiHelper.feedbackQuestion(request);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if(response.isSuccessful()){
                    CommonResponse commonResponse = response.body();
                    if(commonResponse.isSuccess()){
                        onSuccess();
                    } else{
                        onError(commonResponse.getError());
                    }
                } else{
                    onError(AppConst.ERROR_CONNECTION);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                onError(t.getMessage());
            }
        });
    }

    private void onSuccess(){
        MyProgressBar.dismiss();
        onBackPressed();
    }

    private void onError(String msg){
        MyProgressBar.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
