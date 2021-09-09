package com.mvvm.network.using.java.ui.dashboard.fragment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mvvm.network.using.java.di.qualifier.TeamAScore;
import com.mvvm.network.using.java.di.qualifier.TeamBScore;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DashboardViewModel extends ViewModel {

    private MutableLiveData<Integer> teamAScore = new MutableLiveData<>();
    private MutableLiveData<Integer> teamBScore = new MutableLiveData<>();

    @Inject
    public DashboardViewModel(@TeamAScore int defaultTeamAScore, @TeamBScore int defaultTeamBScore) {
        this.teamAScore.setValue(defaultTeamAScore);
        this.teamBScore.setValue(defaultTeamBScore);
    }

    public MutableLiveData<Integer> getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(int score) {
        teamAScore.setValue(teamAScore.getValue() + score);
    }

    public MutableLiveData<Integer> getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(int score) {
        teamBScore.setValue(teamBScore.getValue() + score);
    }

    public void reset() {
        teamAScore.setValue(0);
        teamBScore.setValue(0);
    }
}
