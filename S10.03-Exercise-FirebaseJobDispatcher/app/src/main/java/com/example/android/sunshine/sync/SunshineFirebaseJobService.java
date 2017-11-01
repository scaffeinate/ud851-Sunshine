package com.example.android.sunshine.sync;

import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// COMPLETED (2) Make sure you've imported the jobdispatcher.JobService, not job.JobService

// COMPLETED (3) Add a class called SunshineFirebaseJobService that extends jobdispatcher.JobService

//  COMPLETED (4) Declare an ASyncTask field called mFetchWeatherTask

//  COMPLETED (5) Override onStartJob and within it, spawn off a separate ASyncTask to sync weather data
//  COMPLETED (6) Once the weather data is sync'd, call jobFinished with the appropriate arguments
//  COMPLETED (7) Override onStopJob, cancel the ASyncTask if it's not null and return true

public class SunshineFirebaseJobService extends JobService {

    private FetchWeatherTask mFetchWeatherTask;

    @Override
    public boolean onStartJob(JobParameters job) {
        mFetchWeatherTask = new FetchWeatherTask(job);
        mFetchWeatherTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (mFetchWeatherTask != null && !mFetchWeatherTask.isCancelled()) {
            mFetchWeatherTask.cancel(true);
        }
        return true;
    }

    protected class FetchWeatherTask extends AsyncTask<Void, Void, Void> {

        private JobParameters mJob;

        protected FetchWeatherTask(JobParameters job) {
            this.mJob = job;
        }

        @Override
        protected Void doInBackground(Void... params) {
            SunshineSyncTask.syncWeather(SunshineFirebaseJobService.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            jobFinished(mJob, false);
        }
    }
}
