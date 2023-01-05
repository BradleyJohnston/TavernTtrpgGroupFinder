package com.sontath.tavernttrpggroupfinder.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sontath.tavernttrpggroupfinder.BuisinessLogic.DynamoDatabaseDao
import com.sontath.tavernttrpggroupfinder.BuisinessLogic.FirebaseUserLogin
import com.sontath.tavernttrpggroupfinder.BuisinessLogic.IDatabaseDao
import com.sontath.tavernttrpggroupfinder.BuisinessLogic.IUserLogin
import com.sontath.tavernttrpggroupfinder.R
import com.sontath.tavernttrpggroupfinder.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var _genericProfile: genericProfileUiState
    private var _gameProfileList: ArrayList<gameProfileUiState> = ArrayList()
    private lateinit var _playerProfile: playerProfileUiState
    private lateinit var _databaseDao: IDatabaseDao
    private lateinit var _userLogin: IUserLogin

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // RecyclerView for the game profile list
    private lateinit var gameProfileRecyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gameProfileAdapter: gameProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initializeDatabaseDao()
        initializeUserLogin()
        loadProfiles()
        initializeProfiles(profileViewModel)

        return root
    }

    private fun initializeUserLogin() {
        _userLogin = FirebaseUserLogin()
    }

    private fun initializeDatabaseDao() {
         _databaseDao = DynamoDatabaseDao()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadProfiles() {
        loadGenericProfile()
        loadPlayerProfile()
        loadGameProfiles()
    }

    private fun loadGenericProfile() {
        _genericProfile = _databaseDao.readGenericProfile(_userLogin.getUid()) // TODO: Determine return value stuff
    }

    private fun loadPlayerProfile() {
        _playerProfile = _databaseDao.readPlayerProfile(_userLogin.getUid())// TODO: Determine return value stuff
    }

    private fun loadGameProfiles() {
        _gameProfileList = _databaseDao.readGameProfiles(_userLogin.getUid())// TODO: Determine return value stuff
    }

    private fun initializeProfiles(profileViewModel: ProfileViewModel) {
        setGenericStrings(profileViewModel)
        bindGenericProfile(profileViewModel)
        setPlayerString(profileViewModel)
        bindPlayerProfile(profileViewModel)
        bindGameRecyclerView()
    }

    private fun setGenericStrings(profileViewModel: ProfileViewModel) {
        profileViewModel.genericNameString = binding.root.context.getString(R.string.profile_name)
        profileViewModel.genericGenderString = binding.root.context.getString(R.string.profile_gender)
        profileViewModel.genericAgeString = binding.root.context.getString(R.string.profile_age)
    }

    private fun bindGenericProfile(profileViewModel: ProfileViewModel) {
        val nameTextView: TextView = binding.textViewName
        val genderTextView: TextView = binding.textViewGender
        val ageTextView: TextView = binding.textViewAge

        profileViewModel.genericName.observe(viewLifecycleOwner) {
            nameTextView.text = it
        }
        profileViewModel.genericGender.observe(viewLifecycleOwner) {
            genderTextView.text = it
        }
        profileViewModel.genericAge.observe(viewLifecycleOwner) {
            ageTextView.text = it
        }
    }

    private fun setPlayerString(profileViewModel: ProfileViewModel) {
        profileViewModel.playerVersionString = binding.root.context.getString(R.string.person_preferred_game_version)
        profileViewModel.playerArchetypeString = binding.root.context.getString(R.string.person_preferred_class_archetype)
        profileViewModel.playerPartySizeString = binding.root.context.getString(R.string.person_preferred_party_size)
    }

    private  fun bindPlayerProfile(profileViewModel: ProfileViewModel) {
        val personVersion: TextView = binding.textViewPersonVersion
        val personArchetype: TextView = binding.textViewPersonArchetype
        val personPartySize: TextView = binding.textViewPersonPartySize

        profileViewModel.playerVersion.observe(viewLifecycleOwner) {
            personVersion.text = it
        }
        profileViewModel.playerArchetype.observe(viewLifecycleOwner) {
            personArchetype.text = it
        }
        profileViewModel.playerPartySize.observe(viewLifecycleOwner) {
            personPartySize.text = it
        }
    }

    private fun bindGameRecyclerView() {
        linearLayoutManager = LinearLayoutManager(context)
        gameProfileRecyclerView = binding.gameProfileRecyclerView
        gameProfileAdapter = gameProfileAdapter(binding.root.context, _gameProfileList)
        gameProfileRecyclerView.adapter = gameProfileAdapter
        gameProfileRecyclerView.layoutManager = linearLayoutManager
    }
}